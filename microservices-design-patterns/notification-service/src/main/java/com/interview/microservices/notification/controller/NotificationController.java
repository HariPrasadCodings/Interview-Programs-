package com.interview.microservices.notification.controller;

import com.interview.microservices.notification.entity.Notification;
import com.interview.microservices.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing notification query endpoints.
 *
 * <p>Note that notifications are <b>created</b> exclusively via Kafka event
 * listeners (see {@link com.interview.microservices.notification.event.OrderEventListener}
 * and {@link com.interview.microservices.notification.event.PaymentEventListener}).
 * This controller only provides read access for downstream consumers
 * (e.g., a frontend polling for user notifications).
 *
 * <p>Endpoints:
 * <ul>
 *   <li>GET /api/notifications/user/{userId} - list all notifications for a user</li>
 *   <li>GET /api/notifications/{id}          - retrieve a specific notification</li>
 * </ul>
 *
 * Interview Tip:
 *   "In an event-driven system, the write path (Kafka listeners) and the
 *    read path (REST API) are decoupled. This is a lightweight form of
 *    CQRS: events drive writes, while REST serves reads."
 */
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Retrieves all notifications for a specific user.
     *
     * @param userId the user's ID
     * @return list of notifications ordered by most recent first
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUserId(
            @PathVariable Long userId) {
        log.info("GET /api/notifications/user/{}", userId);
        List<Notification> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Retrieves a single notification by its ID.
     *
     * @param id the notification ID
     * @return the notification entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        log.info("GET /api/notifications/{}", id);
        Notification notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }
}
