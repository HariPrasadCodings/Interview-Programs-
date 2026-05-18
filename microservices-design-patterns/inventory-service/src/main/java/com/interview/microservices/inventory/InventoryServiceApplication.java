package com.interview.microservices.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Inventory Service - manages product stock levels and demonstrates
 * Async Messaging with Apache Kafka.
 *
 * <p>Key patterns demonstrated:
 * <ul>
 *   <li><b>Async Messaging</b> - Kafka consumer/producer for order and
 *       inventory events</li>
 *   <li><b>Saga (Choreography)</b> - reserve/release inventory as part
 *       of a distributed transaction</li>
 *   <li><b>Database per Service</b> - dedicated H2 in-memory database</li>
 * </ul>
 *
 * <p>Endpoints exposed:
 * <ul>
 *   <li>GET  /api/inventory/{productId} - check inventory for a product</li>
 *   <li>POST /api/inventory/reserve     - reserve stock for an order</li>
 *   <li>POST /api/inventory/release     - release reserved stock (saga compensation)</li>
 *   <li>PUT  /api/inventory/{productId} - update stock levels</li>
 * </ul>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
}
