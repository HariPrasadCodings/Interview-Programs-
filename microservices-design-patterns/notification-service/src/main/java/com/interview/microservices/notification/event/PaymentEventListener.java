package com.interview.microservices.notification.event;

import com.interview.microservices.common.enums.PaymentStatus;
import com.interview.microservices.common.event.NotificationEvent;
import com.interview.microservices.common.event.PaymentEvent;
import com.interview.microservices.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Kafka listener that consumes events from the <b>payment-events</b> topic
 * and translates them into user-facing notifications.
 *
 * <p>This class complements {@link OrderEventListener} to show how a single
 * consumer service can subscribe to multiple event streams. Together they
 * demonstrate that event-driven consumers are independently deployable and
 * horizontally scalable.
 *
 * <p>Handled scenarios:
 * <ul>
 *   <li>COMPLETED -> Payment success notification (email)</li>
 *   <li>FAILED    -> Payment failure notification (email + SMS)</li>
 *   <li>REFUNDED  -> Refund confirmation notification (email)</li>
 * </ul>
 *
 * Interview Tip:
 *   "Each @KafkaListener with a different topic can have its own
 *    containerFactory, allowing independent deserialization, concurrency,
 *    and error-handling strategies per event type."
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventListener {

    private final NotificationService notificationService;

    /**
     * Consumes {@link PaymentEvent}s from the "payment-events" Kafka topic.
     *
     * @param event the payment event published by payment-service
     */
    @KafkaListener(
            topics = "payment-events",
            groupId = "notification-group",
            containerFactory = "paymentEventKafkaListenerContainerFactory"
    )
    public void handlePaymentEvent(PaymentEvent event) {
        log.info("Received PaymentEvent: paymentId={} orderId={} status={} amount={}",
                event.getPaymentId(), event.getOrderId(), event.getStatus(), event.getAmount());

        try {
            NotificationEvent notification = mapToNotification(event);
            if (notification != null) {
                notificationService.sendNotification(notification);
                log.info("Payment notification processed successfully for paymentId={}",
                        event.getPaymentId());
            } else {
                log.debug("No notification required for payment status: {}", event.getStatus());
            }
        } catch (Exception e) {
            log.error("Failed to process payment event for paymentId={}: {}",
                    event.getPaymentId(), e.getMessage(), e);
        }
    }

    /**
     * Maps a {@link PaymentEvent} to a {@link NotificationEvent} based on
     * the payment status. Returns null for statuses that do not warrant
     * a user notification (e.g., PENDING, PROCESSING).
     */
    private NotificationEvent mapToNotification(PaymentEvent event) {
        if (event.getStatus() == null) {
            return null;
        }

        // Note: PaymentEvent does not carry userId directly; in a real system
        // we would look up the userId from the orderId. For this demo, we use
        // the orderId as a proxy (or a fixed userId for illustration).
        Long userId = event.getOrderId();

        return switch (event.getStatus()) {
            case COMPLETED -> NotificationEvent.builder()
                    .userId(userId)
                    .type("PAYMENT_SUCCESS")
                    .subject("Payment Successful - Order #" + event.getOrderId())
                    .message(String.format(
                            "Your payment of $%s for order #%d has been processed successfully. " +
                            "Transaction ID: %s.",
                            event.getAmount(), event.getOrderId(), event.getTransactionId()))
                    .channel("EMAIL")
                    .timestamp(LocalDateTime.now())
                    .build();

            case FAILED -> NotificationEvent.builder()
                    .userId(userId)
                    .type("PAYMENT_FAILURE")
                    .subject("Payment Failed - Order #" + event.getOrderId())
                    .message(String.format(
                            "We were unable to process your payment of $%s for order #%d. " +
                            "Please update your payment method and try again.",
                            event.getAmount(), event.getOrderId()))
                    .channel("EMAIL")
                    .timestamp(LocalDateTime.now())
                    .build();

            case REFUNDED -> NotificationEvent.builder()
                    .userId(userId)
                    .type("PAYMENT_REFUND")
                    .subject("Refund Processed - Order #" + event.getOrderId())
                    .message(String.format(
                            "A refund of $%s for order #%d has been processed. " +
                            "Transaction ID: %s. Please allow 3-5 business days for the " +
                            "amount to appear in your account.",
                            event.getAmount(), event.getOrderId(), event.getTransactionId()))
                    .channel("EMAIL")
                    .timestamp(LocalDateTime.now())
                    .build();

            default -> null;
        };
    }
}
