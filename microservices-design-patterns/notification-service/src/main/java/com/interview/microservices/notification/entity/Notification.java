package com.interview.microservices.notification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * JPA entity representing a notification sent (or queued) for a user.
 *
 * <p>This entity lives in the notification-service's own database, illustrating
 * the <b>Database-per-Service</b> pattern. Each notification is created in
 * response to a domain event received via Kafka (e.g., order confirmed,
 * payment completed) and records the delivery channel and status.
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code type}    - category of notification (ORDER_CONFIRMATION, PAYMENT_SUCCESS, etc.)</li>
 *   <li>{@code channel} - delivery channel (EMAIL, SMS, PUSH)</li>
 *   <li>{@code sent}    - whether the notification was successfully dispatched</li>
 * </ul>
 */
@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 2000)
    private String message;

    @Column(nullable = false)
    @Builder.Default
    private String channel = "EMAIL";

    @Column(nullable = false)
    @Builder.Default
    private Boolean sent = false;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
