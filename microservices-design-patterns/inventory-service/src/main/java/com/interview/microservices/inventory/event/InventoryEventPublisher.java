package com.interview.microservices.inventory.event;

import com.interview.microservices.common.event.InventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Publishes {@link InventoryEvent} messages to the {@code inventory-events}
 * Kafka topic.
 *
 * <p><b>Interview note:</b> This is the "producer" side of the Async
 * Messaging pattern. After the inventory service reserves or releases
 * stock, it publishes an event so that downstream services (saga
 * orchestrator, notification service) can react asynchronously.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryEventPublisher {

    private static final String TOPIC = "inventory-events";

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    /**
     * Publish an inventory event to Kafka.
     *
     * @param orderId            the associated order ID
     * @param productQuantities  map of productId to quantity
     * @param status             the event status (RESERVED, RELEASED, INSUFFICIENT)
     */
    public void publishInventoryEvent(Long orderId,
                                      Map<Long, Integer> productQuantities,
                                      String status) {
        InventoryEvent event = InventoryEvent.builder()
                .orderId(orderId)
                .productQuantities(productQuantities)
                .status(status)
                .timestamp(LocalDateTime.now())
                .build();

        CompletableFuture<SendResult<String, InventoryEvent>> future =
                kafkaTemplate.send(TOPIC, String.valueOf(orderId), event);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to publish inventory event for order {}: {}",
                        orderId, ex.getMessage(), ex);
            } else {
                log.info("Published inventory event [status={}] for order {} "
                                + "to topic {} (partition={}, offset={})",
                        status, orderId, TOPIC,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        });
    }
}
