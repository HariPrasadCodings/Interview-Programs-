package com.interview.microservices.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Payment Service - processes payments and publishes events to Kafka.
 *
 * <p>Demonstrates the <b>Database-per-Service</b> pattern: this service
 * owns its own PostgreSQL database (H2 in dev), completely independent
 * of the datastores used by Order, Product, or Inventory services.
 *
 * <p>Endpoints exposed:
 * <ul>
 *   <li>POST /api/payments/process       - process a payment for an order</li>
 *   <li>POST /api/payments/refund/{id}   - refund a payment (saga compensation)</li>
 *   <li>GET  /api/payments/order/{id}    - retrieve payment by order ID</li>
 * </ul>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
