package com.interview.microservices.saga.client;

import com.interview.microservices.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Feign client for communicating with the Inventory Service.
 *
 * Used by the orchestration-based saga to reserve and release inventory.
 * Reservation is the forward action; release is the compensating action.
 *
 * The service name "inventory-service" is resolved via Eureka, enabling
 * location-transparent communication between microservices.
 */
@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @PostMapping("/api/inventory/reserve/{orderId}")
    ApiResponse<?> reserveInventory(@PathVariable("orderId") Long orderId);

    @PostMapping("/api/inventory/release/{orderId}")
    ApiResponse<?> releaseInventory(@PathVariable("orderId") Long orderId);
}
