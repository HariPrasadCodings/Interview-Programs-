package com.interview.microservices.saga.client;

import com.interview.microservices.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Feign client for communicating with the Order Service.
 *
 * Used by the orchestration-based saga to confirm or cancel orders.
 * The service name "order-service" is resolved via Eureka service discovery,
 * so no hardcoded URL is needed.
 *
 * Interview notes:
 * - Feign provides declarative REST clients, reducing boilerplate.
 * - Service discovery (Eureka) + client-side load balancing ensure
 *   requests are routed to healthy instances.
 * - Feign integrates with Resilience4j for circuit breaking (configured
 *   in order-service itself).
 */
@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @PostMapping("/api/orders/{orderId}/confirm")
    ApiResponse<?> confirmOrder(@PathVariable("orderId") Long orderId);

    @PostMapping("/api/orders/{orderId}/cancel")
    ApiResponse<?> cancelOrder(@PathVariable("orderId") Long orderId);
}
