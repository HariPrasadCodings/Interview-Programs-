package com.interview.microservices.order.event;

import com.interview.microservices.common.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Publishes OrderEvent messages to Kafka topics.
 *
 * Interview Tip:
 *   "This is the Async Messaging pattern. Instead of synchronous HTTP calls
 *    to notify downstream services (payment, inventory, notification), we
 *    publish an event to Kafka. This decouples the producer from consumers
 *    and provides:
 *      1. Temporal decoupling  - consumer can be offline temporarily
 *      2. Spatial decoupling   - producer doesn't know consumer addresses
 *      3. Buffering            - Kafka retains messages for replay
 *      4. Fan-out              - multiple consumers can process the same event"
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final String TOPIC = "order-events";

    /**
     * Publishes an OrderEvent to the "order-events" topic.
     *
     * The orderId is used as the Kafka message key, which ensures:
     *   - All events for the same order go to the same partition
     *   - Events for the same order are processed in order (partition ordering)
     *   - Enables log compaction on the topic if needed
     */
    public void publishOrderEvent(OrderEvent event) {
        String key = String.valueOf(event.getOrderId());

        log.info("Publishing OrderEvent: orderId={}, status={}, topic={}",
                event.getOrderId(), event.getStatus(), TOPIC);

        CompletableFuture<SendResult<String, OrderEvent>> future =
                kafkaTemplate.send(TOPIC, key, event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to publish OrderEvent for orderId={}: {}",
                        event.getOrderId(), ex.getMessage(), ex);
            } else {
                log.info("OrderEvent published successfully: orderId={}, partition={}, offset={}",
                        event.getOrderId(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        });
    }
}
