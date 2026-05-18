package com.interview.microservices.notification.event;

import com.interview.microservices.common.enums.OrderStatus;
import com.interview.microservices.common.event.NotificationEvent;
import com.interview.microservices.common.event.OrderEvent;
import com.interview.microservices.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Kafka listener that consumes events from the <b>order-events</b> topic
 * and translates them into user-facing notifications.
 *
 * <p>This class demonstrates the <b>Event-Driven Architecture</b> pattern:
 * the order-service publishes {@link OrderEvent}s without any knowledge of
 * this consumer. The notification-service independently decides how to
 * react to each event type.
 *
 * <p>Handled scenarios:
 * <ul>
 *   <li>CONFIRMED  -> Order confirmation notification (email)</li>
 *   <li>CANCELLED  -> Order cancellation notification (email)</li>
 *   <li>SHIPPING   -> Shipping update notification (push)</li>
 *   <li>DELIVERED  -> Delivery confirmation notification (push)</li>
 * </ul>
 *
 * Interview Tip:
 *   "@KafkaListener is a Spring Kafka annotation that marks a method as a
 *    Kafka consumer. The containerFactory references a
 *    ConcurrentKafkaListenerContainerFactory bean that defines
 *    deserialization, concurrency, and error handling settings."
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventListener {

    private final NotificationService notificationService;

    /**
     * Consumes {@link OrderEvent}s from the "order-events" Kafka topic.
     *
     * @param event the order event published by order-service
     */
    @KafkaListener(
            topics = "order-events",
            groupId = "notification-group",
            containerFactory = "orderEventKafkaListenerContainerFactory"
    )
    public void handleOrderEvent(OrderEvent event) {
        log.info("Received OrderEvent: orderId={} status={} userId={}",
                event.getOrderId(), event.getStatus(), event.getUserId());

        try {
            NotificationEvent notification = mapToNotification(event);
            if (notification != null) {
                notificationService.sendNotification(notification);
                log.info("Order notification processed successfully for orderId={}", event.getOrderId());
            } else {
                log.debug("No notification required for order status: {}", event.getStatus());
            }
        } catch (Exception e) {
            log.error("Failed to process order event for orderId={}: {}",
                    event.getOrderId(), e.getMessage(), e);
        }
    }

    /**
     * Maps an {@link OrderEvent} to a {@link NotificationEvent} based on the
     * order status. Returns null for statuses that do not warrant a user notification.
     */
    private NotificationEvent mapToNotification(OrderEvent event) {
        if (event.getStatus() == null) {
            return null;
        }

        return switch (event.getStatus()) {
            case CONFIRMED -> NotificationEvent.builder()
                    .userId(event.getUserId())
                    .type("ORDER_CONFIRMATION")
                    .subject("Order Confirmed - #" + event.getOrderId())
                    .message(String.format(
                            "Your order #%d has been confirmed! Total amount: $%s. " +
                            "We will notify you when it ships.",
                            event.getOrderId(), event.getTotalAmount()))
                    .channel("EMAIL")
                    .timestamp(LocalDateTime.now())
                    .build();

            case CANCELLED, ROLLBACK -> NotificationEvent.builder()
                    .userId(event.getUserId())
                    .type("ORDER_CANCELLATION")
                    .subject("Order Cancelled - #" + event.getOrderId())
                    .message(String.format(
                            "Your order #%d has been cancelled. If you were charged, " +
                            "a refund will be processed within 3-5 business days.",
                            event.getOrderId()))
                    .channel("EMAIL")
                    .timestamp(LocalDateTime.now())
                    .build();

            case SHIPPING -> NotificationEvent.builder()
                    .userId(event.getUserId())
                    .type("ORDER_SHIPPING")
                    .subject("Order Shipped - #" + event.getOrderId())
                    .message(String.format(
                            "Great news! Your order #%d is on its way.",
                            event.getOrderId()))
                    .channel("PUSH")
                    .timestamp(LocalDateTime.now())
                    .build();

            case DELIVERED -> NotificationEvent.builder()
                    .userId(event.getUserId())
                    .type("ORDER_DELIVERED")
                    .subject("Order Delivered - #" + event.getOrderId())
                    .message(String.format(
                            "Your order #%d has been delivered. Enjoy!",
                            event.getOrderId()))
                    .channel("PUSH")
                    .timestamp(LocalDateTime.now())
                    .build();

            default -> null;
        };
    }
}
