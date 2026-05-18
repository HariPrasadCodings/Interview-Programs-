package com.interview.microservices.payment.event;

import com.interview.microservices.common.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Publishes {@link PaymentEvent} messages to Kafka.
 *
 * <p>This component is used by the payment service to broadcast
 * payment outcomes (COMPLETED, FAILED, REFUNDED) so that other
 * services (e.g. saga-orchestrator, notification-service) can
 * react asynchronously.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventPublisher {

    private static final String TOPIC = "payment-events";

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    /**
     * Publish a payment event to the {@code payment-events} Kafka topic.
     *
     * @param event the payment event to publish
     */
    public void publish(PaymentEvent event) {
        log.info("Publishing PaymentEvent to topic '{}': orderId={}, status={}",
                TOPIC, event.getOrderId(), event.getStatus());

        CompletableFuture<SendResult<String, PaymentEvent>> future =
                kafkaTemplate.send(TOPIC, String.valueOf(event.getOrderId()), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to publish PaymentEvent for orderId={}: {}",
                        event.getOrderId(), ex.getMessage(), ex);
            } else {
                log.info("PaymentEvent published successfully: topic={}, partition={}, offset={}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        });
    }
}
