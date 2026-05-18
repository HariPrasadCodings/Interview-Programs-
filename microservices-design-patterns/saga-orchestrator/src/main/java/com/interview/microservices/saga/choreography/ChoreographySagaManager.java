package com.interview.microservices.saga.choreography;

import com.interview.microservices.common.enums.OrderStatus;
import com.interview.microservices.common.enums.PaymentStatus;
import com.interview.microservices.common.enums.SagaStatus;
import com.interview.microservices.common.event.InventoryEvent;
import com.interview.microservices.common.event.OrderEvent;
import com.interview.microservices.common.event.PaymentEvent;
import com.interview.microservices.saga.entity.SagaInstance;
import com.interview.microservices.saga.repository.SagaInstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Choreography-based Saga Manager using Kafka events.
 *
 * Unlike the orchestration approach, there is NO central coordinator.
 * Each service publishes domain events, and other services react
 * to those events by performing their part of the distributed transaction.
 *
 * Event Flow (happy path):
 *   1. Order created -> publishes OrderEvent (status=CREATED) to "order-events"
 *   2. This manager listens, records saga state, publishes to "inventory-events"
 *      requesting reservation
 *   3. Inventory-service reserves stock -> publishes InventoryEvent (status=RESERVED)
 *   4. This manager listens, updates saga, publishes to "payment-events"
 *      requesting payment
 *   5. Payment-service processes payment -> publishes PaymentEvent (status=COMPLETED)
 *   6. This manager listens, updates saga, publishes order confirmation
 *
 * Compensation Flow (on failure):
 *   - If inventory reservation fails (status=INSUFFICIENT), saga marks FAILED
 *   - If payment fails (status=FAILED), saga publishes inventory release event
 *   - Each compensating event triggers the appropriate service to undo its action
 *
 * Interview notes:
 * - Choreography is more decoupled than orchestration; no single point of failure.
 * - Harder to debug and trace because the flow is implicit in event subscriptions.
 * - Event ordering and idempotency are critical concerns.
 * - This manager acts as a "saga participant" that tracks state, but in a pure
 *   choreography each service would independently track its own concerns.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChoreographySagaManager {

    private final SagaInstanceRepository sagaInstanceRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Topic names
    private static final String ORDER_EVENTS_TOPIC = "order-events";
    private static final String INVENTORY_EVENTS_TOPIC = "inventory-events";
    private static final String PAYMENT_EVENTS_TOPIC = "payment-events";

    /**
     * Initiates a choreography-based saga by publishing an OrderEvent.
     *
     * @param orderId the order to start the saga for
     * @return the created SagaInstance
     */
    public SagaInstance startSaga(Long orderId) {
        log.info("Starting choreography saga for order: {}", orderId);

        SagaInstance sagaInstance = SagaInstance.builder()
                .orderId(orderId)
                .currentStep("CREATE_ORDER")
                .status(SagaStatus.STARTED)
                .build();
        sagaInstance = sagaInstanceRepository.save(sagaInstance);

        // Publish order created event to kick off the choreography
        OrderEvent orderEvent = OrderEvent.builder()
                .orderId(orderId)
                .status(OrderStatus.CREATED)
                .build();
        kafkaTemplate.send(ORDER_EVENTS_TOPIC, orderId.toString(), orderEvent);
        log.info("Published OrderEvent (CREATED) to topic '{}' for order: {}", ORDER_EVENTS_TOPIC, orderId);

        return sagaInstance;
    }

    /**
     * Listens to order events. When an order is created, triggers
     * inventory reservation by publishing an InventoryEvent.
     */
    @KafkaListener(topics = ORDER_EVENTS_TOPIC, groupId = "saga-choreography-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderEvent(OrderEvent event) {
        log.info("Received OrderEvent: orderId={}, status={}", event.getOrderId(), event.getStatus());

        if (event.getStatus() == OrderStatus.CREATED) {
            // Trigger inventory reservation
            InventoryEvent inventoryEvent = InventoryEvent.builder()
                    .orderId(event.getOrderId())
                    .status("RESERVE_REQUESTED")
                    .build();
            kafkaTemplate.send(INVENTORY_EVENTS_TOPIC, event.getOrderId().toString(), inventoryEvent);
            log.info("Published InventoryEvent (RESERVE_REQUESTED) for order: {}", event.getOrderId());
        }
    }

    /**
     * Listens to inventory events. On successful reservation, triggers
     * payment processing. On failure, marks the saga as failed.
     */
    @KafkaListener(topics = INVENTORY_EVENTS_TOPIC, groupId = "saga-choreography-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleInventoryEvent(InventoryEvent event) {
        log.info("Received InventoryEvent: orderId={}, status={}", event.getOrderId(), event.getStatus());

        sagaInstanceRepository.findByOrderId(event.getOrderId()).ifPresent(saga -> {
            switch (event.getStatus()) {
                case "RESERVED" -> {
                    // Inventory reserved - proceed to payment
                    saga.setCurrentStep("RESERVE_INVENTORY");
                    saga.setStatus(SagaStatus.INVENTORY_RESERVED);
                    sagaInstanceRepository.save(saga);

                    PaymentEvent paymentEvent = PaymentEvent.builder()
                            .orderId(event.getOrderId())
                            .status(PaymentStatus.PENDING)
                            .build();
                    kafkaTemplate.send(PAYMENT_EVENTS_TOPIC, event.getOrderId().toString(), paymentEvent);
                    log.info("Published PaymentEvent (PENDING) for order: {}", event.getOrderId());
                }
                case "INSUFFICIENT" -> {
                    // Inventory insufficient - saga fails, no compensation needed
                    log.warn("Inventory insufficient for order: {}. Saga failed.", event.getOrderId());
                    saga.setCurrentStep("RESERVE_INVENTORY_FAILED");
                    saga.setStatus(SagaStatus.FAILED);
                    sagaInstanceRepository.save(saga);
                }
                case "RELEASED" -> {
                    // Compensating action completed
                    log.info("Inventory released (compensation) for order: {}", event.getOrderId());
                    saga.setStatus(SagaStatus.COMPENSATED);
                    sagaInstanceRepository.save(saga);
                }
                default -> log.warn("Unhandled inventory event status: {} for order: {}",
                        event.getStatus(), event.getOrderId());
            }
        });
    }

    /**
     * Listens to payment events. On successful payment, confirms the order.
     * On failure, triggers inventory release as compensation.
     */
    @KafkaListener(topics = PAYMENT_EVENTS_TOPIC, groupId = "saga-choreography-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handlePaymentEvent(PaymentEvent event) {
        log.info("Received PaymentEvent: orderId={}, status={}", event.getOrderId(), event.getStatus());

        sagaInstanceRepository.findByOrderId(event.getOrderId()).ifPresent(saga -> {
            if (event.getStatus() == PaymentStatus.COMPLETED) {
                // Payment succeeded - confirm the order
                saga.setCurrentStep("PROCESS_PAYMENT");
                saga.setStatus(SagaStatus.PAYMENT_PROCESSED);
                sagaInstanceRepository.save(saga);

                // Publish order confirmation event
                OrderEvent confirmEvent = OrderEvent.builder()
                        .orderId(event.getOrderId())
                        .status(OrderStatus.CONFIRMED)
                        .build();
                kafkaTemplate.send(ORDER_EVENTS_TOPIC, event.getOrderId().toString(), confirmEvent);

                // Mark saga as completed
                saga.setCurrentStep("CONFIRM_ORDER");
                saga.setStatus(SagaStatus.ORDER_CONFIRMED);
                sagaInstanceRepository.save(saga);
                log.info("Choreography saga completed for order: {}", event.getOrderId());

            } else if (event.getStatus() == PaymentStatus.FAILED) {
                // Payment failed - compensate by releasing inventory
                log.warn("Payment failed for order: {}. Starting compensation...", event.getOrderId());
                saga.setCurrentStep("PROCESS_PAYMENT_FAILED");
                saga.setStatus(SagaStatus.COMPENSATING);
                sagaInstanceRepository.save(saga);

                // Publish inventory release event as compensation
                InventoryEvent releaseEvent = InventoryEvent.builder()
                        .orderId(event.getOrderId())
                        .status("RELEASE_REQUESTED")
                        .build();
                kafkaTemplate.send(INVENTORY_EVENTS_TOPIC, event.getOrderId().toString(), releaseEvent);
                log.info("Published InventoryEvent (RELEASE_REQUESTED) as compensation for order: {}",
                        event.getOrderId());
            }
        });
    }
}
