package com.interview.microservices.inventory.event;

import com.interview.microservices.common.enums.OrderStatus;
import com.interview.microservices.common.event.OrderEvent;
import com.interview.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Kafka consumer that listens to the {@code order-events} topic and
 * automatically reserves or releases inventory based on the order status.
 *
 * <p><b>Interview note:</b> This is the "consumer" side of the Async
 * Messaging pattern and demonstrates <b>Saga choreography</b>: each
 * service reacts to events from other services without a central
 * orchestrator.
 *
 * <ul>
 *   <li>{@code CREATED} - triggers inventory reservation</li>
 *   <li>{@code CANCELLED} / {@code PAYMENT_FAILED} / {@code ROLLBACK}
 *       - triggers inventory release (compensation)</li>
 * </ul>
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryEventListener {

    private final InventoryService inventoryService;
    private final InventoryEventPublisher eventPublisher;

    /**
     * Consume order events from the {@code order-events} Kafka topic.
     *
     * @param orderEvent the incoming order event
     */
    @KafkaListener(
            topics = "order-events",
            groupId = "inventory-service-group",
            containerFactory = "orderEventListenerFactory"
    )
    public void handleOrderEvent(OrderEvent orderEvent) {
        log.info("Received order event: orderId={}, status={}",
                orderEvent.getOrderId(), orderEvent.getStatus());

        Map<Long, Integer> productQuantities = orderEvent.getItems().stream()
                .collect(Collectors.toMap(
                        OrderEvent.OrderItemEvent::getProductId,
                        OrderEvent.OrderItemEvent::getQuantity
                ));

        if (orderEvent.getStatus() == OrderStatus.CREATED) {
            handleOrderCreated(orderEvent.getOrderId(), productQuantities);
        } else if (orderEvent.getStatus() == OrderStatus.CANCELLED
                || orderEvent.getStatus() == OrderStatus.PAYMENT_FAILED
                || orderEvent.getStatus() == OrderStatus.ROLLBACK) {
            handleOrderCancelled(orderEvent.getOrderId(), productQuantities);
        }
    }

    /**
     * Attempt to reserve inventory when a new order is created.
     * Publishes a RESERVED or INSUFFICIENT event back to Kafka.
     */
    private void handleOrderCreated(Long orderId, Map<Long, Integer> productQuantities) {
        try {
            inventoryService.reserveInventory(orderId, productQuantities);
            eventPublisher.publishInventoryEvent(orderId, productQuantities, "RESERVED");
            log.info("Inventory reserved successfully for order {}", orderId);
        } catch (IllegalStateException e) {
            log.warn("Inventory reservation failed for order {}: {}",
                    orderId, e.getMessage());
            eventPublisher.publishInventoryEvent(orderId, productQuantities, "INSUFFICIENT");
        }
    }

    /**
     * Release reserved inventory when an order is cancelled or payment
     * fails (saga compensation step).
     */
    private void handleOrderCancelled(Long orderId, Map<Long, Integer> productQuantities) {
        try {
            inventoryService.releaseInventory(orderId, productQuantities);
            eventPublisher.publishInventoryEvent(orderId, productQuantities, "RELEASED");
            log.info("Inventory released successfully for order {}", orderId);
        } catch (Exception e) {
            log.error("Failed to release inventory for order {}: {}",
                    orderId, e.getMessage(), e);
        }
    }
}
