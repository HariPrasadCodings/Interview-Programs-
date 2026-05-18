package com.interview.microservices.inventory.service;

import com.interview.microservices.common.dto.InventoryDto;
import com.interview.microservices.inventory.entity.Inventory;
import com.interview.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Core inventory management service.
 *
 * <p>Provides atomic reserve/release operations that participate in the
 * Saga pattern for distributed transactions. All mutating methods are
 * annotated with {@link Transactional} so that partial updates are
 * rolled back on failure.
 *
 * <p><b>Interview note:</b> This service demonstrates how the Saga
 * choreography pattern works on the inventory side - an order event
 * triggers {@link #reserveInventory}, and if the payment fails later,
 * a compensation event triggers {@link #releaseInventory}.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * Check whether the requested quantity is available for a product.
     *
     * @param productId the product to check
     * @param quantity  the desired quantity
     * @return true if enough stock is available
     */
    @Transactional(readOnly = true)
    public boolean checkAvailability(Long productId, Integer quantity) {
        return inventoryRepository.findByProductId(productId)
                .map(inv -> inv.getAvailableQuantity() >= quantity)
                .orElse(false);
    }

    /**
     * Reserve inventory for an order. Moves stock from
     * {@code availableQuantity} to {@code reservedQuantity}.
     *
     * <p>This is the "forward" step of the Saga: if reservation
     * succeeds, the saga continues to payment. If it fails (insufficient
     * stock), the saga is compensated immediately.
     *
     * @param orderId            the order requesting the reservation
     * @param productQuantities  map of productId to quantity to reserve
     * @return true if all products were successfully reserved
     * @throws IllegalStateException if any product has insufficient stock
     */
    @Transactional
    public boolean reserveInventory(Long orderId, Map<Long, Integer> productQuantities) {
        log.info("Reserving inventory for order {}: {}", orderId, productQuantities);

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            Inventory inventory = inventoryRepository.findByProductId(productId)
                    .orElseThrow(() -> new IllegalStateException(
                            "No inventory record found for product: " + productId));

            if (inventory.getAvailableQuantity() < quantity) {
                log.warn("Insufficient stock for product {} (requested={}, available={})",
                        productId, quantity, inventory.getAvailableQuantity());
                throw new IllegalStateException(
                        "Insufficient stock for product: " + productId
                                + " (requested=" + quantity
                                + ", available=" + inventory.getAvailableQuantity() + ")");
            }

            inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
            inventory.setReservedQuantity(inventory.getReservedQuantity() + quantity);
            inventoryRepository.save(inventory);

            log.info("Reserved {} units of product {} for order {} "
                            + "(available={}, reserved={})",
                    quantity, productId, orderId,
                    inventory.getAvailableQuantity(), inventory.getReservedQuantity());
        }

        return true;
    }

    /**
     * Release previously reserved inventory (saga compensation).
     *
     * <p>This is the "compensating" step: if payment fails after
     * inventory was reserved, the reserved quantities are moved back
     * to available stock.
     *
     * @param orderId            the order whose reservation should be undone
     * @param productQuantities  map of productId to quantity to release
     */
    @Transactional
    public void releaseInventory(Long orderId, Map<Long, Integer> productQuantities) {
        log.info("Releasing inventory for order {}: {}", orderId, productQuantities);

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            Inventory inventory = inventoryRepository.findByProductId(productId)
                    .orElseThrow(() -> new IllegalStateException(
                            "No inventory record found for product: " + productId));

            inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);
            inventory.setReservedQuantity(
                    Math.max(0, inventory.getReservedQuantity() - quantity));
            inventoryRepository.save(inventory);

            log.info("Released {} units of product {} for order {} "
                            + "(available={}, reserved={})",
                    quantity, productId, orderId,
                    inventory.getAvailableQuantity(), inventory.getReservedQuantity());
        }
    }

    /**
     * Retrieve inventory details for a specific product.
     *
     * @param productId the product to look up
     * @return the inventory DTO
     * @throws IllegalStateException if no inventory record exists
     */
    @Transactional(readOnly = true)
    public InventoryDto getInventoryByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalStateException(
                        "No inventory record found for product: " + productId));

        return toDto(inventory);
    }

    /**
     * Update the available stock for a product. Creates a new inventory
     * record if one does not already exist.
     *
     * @param productId the product to update
     * @param quantity  the new available quantity
     * @return the updated inventory DTO
     */
    @Transactional
    public InventoryDto updateStock(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElse(Inventory.builder()
                        .productId(productId)
                        .availableQuantity(0)
                        .reservedQuantity(0)
                        .build());

        inventory.setAvailableQuantity(quantity);
        inventory = inventoryRepository.save(inventory);

        log.info("Updated stock for product {}: availableQuantity={}",
                productId, quantity);

        return toDto(inventory);
    }

    // ======================== Helper ========================

    private InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .availableQuantity(inventory.getAvailableQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .build();
    }
}
