package com.interview.microservices.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Product Service - implements the CQRS (Command Query Responsibility Segregation)
 * pattern to separate write and read concerns.
 *
 * <h3>Architecture</h3>
 * <ul>
 *   <li><b>Write side:</b> JPA + H2 - handles {@code CreateProductCommand} and
 *       {@code UpdateProductCommand}, persists to the relational store, then
 *       publishes domain events to Kafka.</li>
 *   <li><b>Read side:</b> MongoDB - a denormalised {@code ProductReadModel} is
 *       maintained by {@code ProductEventHandler}, which consumes Kafka events
 *       and projects them into the document store for fast queries.</li>
 * </ul>
 *
 * <h3>Endpoints</h3>
 * <ul>
 *   <li>POST   /api/products       - create a product  (write)</li>
 *   <li>PUT    /api/products/{id}  - update a product  (write)</li>
 *   <li>DELETE /api/products/{id}  - delete a product  (write)</li>
 *   <li>GET    /api/products/{id}  - get product by id (read)</li>
 *   <li>GET    /api/products       - list all products (read)</li>
 *   <li>GET    /api/products/category/{category} - by category (read)</li>
 * </ul>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
