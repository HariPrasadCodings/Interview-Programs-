package com.interview.microservices.inventory.controller;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.InventoryDto;
import com.interview.microservices.inventory.service.InventoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller for inventory management operations.
 *
 * <p>Provides endpoints for querying stock levels, reserving inventory
 * for orders, releasing inventory (saga compensation), and updating
 * stock quantities.
 */
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Get inventory details for a specific product.
     *
     * @param productId the product ID
     * @return the inventory DTO wrapped in an API response
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<InventoryDto>> getInventory(
            @PathVariable Long productId) {
        log.info("GET /api/inventory/{}", productId);
        InventoryDto dto = inventoryService.getInventoryByProductId(productId);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    /**
     * Reserve inventory for an order.
     *
     * @param request the reservation request containing orderId and
     *                product-quantity mappings
     * @return success or failure response
     */
    @PostMapping("/reserve")
    public ResponseEntity<ApiResponse<Boolean>> reserveInventory(
            @Valid @RequestBody ReservationRequest request) {
        log.info("POST /api/inventory/reserve - orderId={}", request.orderId());
        boolean reserved = inventoryService.reserveInventory(
                request.orderId(), request.productQuantities());
        return ResponseEntity.ok(
                ApiResponse.success("Inventory reserved successfully", reserved));
    }

    /**
     * Release previously reserved inventory (saga compensation).
     *
     * @param request the release request containing orderId and
     *                product-quantity mappings
     * @return success response
     */
    @PostMapping("/release")
    public ResponseEntity<ApiResponse<Void>> releaseInventory(
            @Valid @RequestBody ReservationRequest request) {
        log.info("POST /api/inventory/release - orderId={}", request.orderId());
        inventoryService.releaseInventory(
                request.orderId(), request.productQuantities());
        return ResponseEntity.ok(
                ApiResponse.success("Inventory released successfully", null));
    }

    /**
     * Update the available stock for a product.
     *
     * @param productId the product ID
     * @param request   the update request containing the new quantity
     * @return the updated inventory DTO
     */
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<InventoryDto>> updateStock(
            @PathVariable Long productId,
            @Valid @RequestBody StockUpdateRequest request) {
        log.info("PUT /api/inventory/{} - quantity={}", productId, request.quantity());
        InventoryDto dto = inventoryService.updateStock(productId, request.quantity());
        return ResponseEntity.ok(
                ApiResponse.success("Stock updated successfully", dto));
    }

    // ======================== Request Records ========================

    /**
     * Request body for reserve/release operations.
     *
     * @param orderId            the associated order
     * @param productQuantities  map of productId to quantity
     */
    public record ReservationRequest(
            @NotNull(message = "Order ID is required")
            Long orderId,

            @NotNull(message = "Product quantities map is required")
            Map<Long, Integer> productQuantities
    ) {}

    /**
     * Request body for stock update operations.
     *
     * @param quantity the new available quantity
     */
    public record StockUpdateRequest(
            @NotNull(message = "Quantity is required")
            @Positive(message = "Quantity must be positive")
            Integer quantity
    ) {}
}
