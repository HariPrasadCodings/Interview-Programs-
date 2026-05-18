package com.interview.microservices.order.client;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign Client for Inventory Service.
 *
 * Used during order creation to check stock availability and reserve
 * inventory. If inventory-service is down, the circuit breaker in
 * OrderService will open and the fallback method returns a safe default.
 *
 * Interview Tip:
 *   "In a production system, inventory reservation is typically part of
 *    a Saga. The order-service reserves inventory, then processes payment.
 *    If payment fails, a compensating transaction releases the reservation."
 */
@FeignClient(
        name = "inventory-service",
        path = "/api/inventory"
)
public interface InventoryFeignClient {

    @GetMapping("/product/{productId}")
    ApiResponse<InventoryDto> getInventory(@PathVariable("productId") Long productId);

    @PutMapping("/reserve")
    ApiResponse<InventoryDto> reserveInventory(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity
    );

    @PutMapping("/release")
    ApiResponse<InventoryDto> releaseInventory(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity
    );
}
