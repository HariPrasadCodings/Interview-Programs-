package com.interview.microservices.notification.service;

import com.interview.microservices.common.event.NotificationEvent;
import com.interview.microservices.notification.entity.Notification;
import com.interview.microservices.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Core notification service responsible for persisting and "sending"
 * notifications through the appropriate channel.
 *
 * <p>In a production system, the send methods would integrate with
 * real providers (SendGrid for email, Twilio for SMS, Firebase for push).
 * Here we simulate sending with log messages to keep the demo focused
 * on the Event-Driven Architecture pattern.
 *
 * Interview Tip:
 *   "The notification service acts as an event consumer. It doesn't know
 *    or care which service produced the event. This loose coupling means
 *    we can add new event sources (e.g., shipping-service) without
 *    modifying the notification service - we just add a new listener."
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Processes a {@link NotificationEvent} by persisting it and dispatching
     * through the appropriate channel (email, SMS, or push).
     *
     * @param event the notification event received from Kafka
     * @return the saved notification entity
     */
    public Notification sendNotification(NotificationEvent event) {
        log.info("Processing notification for userId={} type={} channel={}",
                event.getUserId(), event.getType(), event.getChannel());

        Notification notification = Notification.builder()
                .userId(event.getUserId())
                .type(event.getType())
                .subject(event.getSubject())
                .message(event.getMessage())
                .channel(event.getChannel() != null ? event.getChannel() : "EMAIL")
                .createdAt(LocalDateTime.now())
                .build();

        // Simulate sending through the appropriate channel
        boolean success = dispatchNotification(notification);
        notification.setSent(success);

        Notification saved = notificationRepository.save(notification);
        log.info("Notification saved: id={} sent={}", saved.getId(), saved.getSent());

        return saved;
    }

    /**
     * Retrieves all notifications for a given user.
     *
     * @param userId the user's ID
     * @return list of notifications, most recent first
     */
    public List<Notification> getNotificationsByUserId(Long userId) {
        log.debug("Fetching notifications for userId={}", userId);
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    /**
     * Retrieves a single notification by its ID.
     *
     * @param id the notification ID
     * @return the notification entity
     * @throws RuntimeException if not found
     */
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    // ======================== Private Helpers ========================

    /**
     * Simulates sending a notification through the appropriate channel.
     * In production, this would call external APIs (SendGrid, Twilio, Firebase).
     *
     * @param notification the notification to dispatch
     * @return true if the simulated send was successful
     */
    private boolean dispatchNotification(Notification notification) {
        String channel = notification.getChannel().toUpperCase();

        switch (channel) {
            case "EMAIL" -> {
                log.info("[EMAIL] Sending email to userId={}: subject='{}', message='{}'",
                        notification.getUserId(), notification.getSubject(), notification.getMessage());
                log.info("[EMAIL] Email sent successfully to userId={}", notification.getUserId());
                return true;
            }
            case "SMS" -> {
                log.info("[SMS] Sending SMS to userId={}: message='{}'",
                        notification.getUserId(), notification.getMessage());
                log.info("[SMS] SMS sent successfully to userId={}", notification.getUserId());
                return true;
            }
            case "PUSH" -> {
                log.info("[PUSH] Sending push notification to userId={}: subject='{}', message='{}'",
                        notification.getUserId(), notification.getSubject(), notification.getMessage());
                log.info("[PUSH] Push notification sent successfully to userId={}", notification.getUserId());
                return true;
            }
            default -> {
                log.warn("Unknown notification channel '{}' for userId={}. Defaulting to EMAIL.",
                        channel, notification.getUserId());
                log.info("[EMAIL] Sending email (fallback) to userId={}: subject='{}'",
                        notification.getUserId(), notification.getSubject());
                return true;
            }
        }
    }
}
