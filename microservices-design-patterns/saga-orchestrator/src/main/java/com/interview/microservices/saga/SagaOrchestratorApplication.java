package com.interview.microservices.saga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Saga Orchestrator Application
 *
 * Demonstrates the Saga Pattern for managing distributed transactions
 * across multiple microservices. Supports two approaches:
 *
 * 1. Orchestration: A central SagaOrchestrator coordinates each step
 *    (reserve inventory -> process payment -> confirm order) sequentially,
 *    and triggers compensating actions on failure.
 *
 * 2. Choreography: Each service publishes domain events to Kafka.
 *    Services react to events they are interested in, with no central
 *    coordinator. The flow emerges from event subscriptions.
 *
 * @EnableFeignClients enables declarative REST clients used by the
 * orchestration approach to call order-service, inventory-service,
 * and payment-service synchronously.
 */
@SpringBootApplication
@EnableFeignClients
public class SagaOrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaOrchestratorApplication.class, args);
    }
}
