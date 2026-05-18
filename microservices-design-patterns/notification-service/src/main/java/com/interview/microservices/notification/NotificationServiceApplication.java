package com.interview.microservices.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Notification Service - Entry Point
 *
 * <p>Demonstrates <b>Event-Driven Architecture</b> via Kafka consumers.
 * This service subscribes to domain event topics (order-events, payment-events)
 * and translates them into user notifications delivered through various channels
 * (email, SMS, push).
 *
 * <p>Key patterns illustrated:
 * <ul>
 *   <li><b>Event-Driven Consumer</b> - Kafka listeners react to upstream events
 *       without tight coupling to the producing services.</li>
 *   <li><b>Database-per-Service</b> - Owns its own H2 (dev) / dedicated database (prod)
 *       for notification persistence and audit trail.</li>
 * </ul>
 *
 * <p>Endpoints exposed:
 * <ul>
 *   <li>GET /api/notifications/user/{userId} - list notifications for a user</li>
 *   <li>GET /api/notifications/{id}          - retrieve a single notification</li>
 * </ul>
 *
 * Interview Tip:
 *   "Event-Driven Architecture decouples producers from consumers. The order-service
 *    publishes an OrderEvent without knowing who consumes it. The notification-service
 *    subscribes independently, enabling loose coupling and easy extensibility."
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
