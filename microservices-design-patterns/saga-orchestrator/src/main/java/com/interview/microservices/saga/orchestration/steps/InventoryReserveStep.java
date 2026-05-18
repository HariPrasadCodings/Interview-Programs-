package com.interview.microservices.saga.orchestration.steps;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.saga.client.InventoryServiceClient;
import com.interview.microservices.saga.orchestration.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Saga step that reserves inventory for an order.
 *
 * Forward action: Calls inventory-service to reserve stock for the
 * order's items. If insufficient stock is available, the step fails
 * and the saga will not proceed to payment.
 *
 * Compensating action: Calls inventory-service to release the
 * previously reserved stock, making it available again.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryReserveStep implements SagaStep {

    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public String getName() {
        return "RESERVE_INVENTORY";
    }

    @Override
    public boolean execute(Long orderId) {
        log.info("Saga step [RESERVE_INVENTORY] - Reserving inventory for order: {}", orderId);
        try {
            ApiResponse<?> response = inventoryServiceClient.reserveInventory(orderId);
            if (response.isSuccess()) {
                log.info("Saga step [RESERVE_INVENTORY] - Inventory reserved successfully for order: {}", orderId);
                return true;
            } else {
                log.warn("Saga step [RESERVE_INVENTORY] - Failed to reserve inventory for order: {}. Reason: {}",
                        orderId, response.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.error("Saga step [RESERVE_INVENTORY] - Exception while reserving inventory for order: {}", orderId, e);
            return false;
        }
    }

    @Override
    public void compensate(Long orderId) {
        log.info("Saga step [RESERVE_INVENTORY] - Compensating: releasing inventory for order: {}", orderId);
        try {
            inventoryServiceClient.releaseInventory(orderId);
            log.info("Saga step [RESERVE_INVENTORY] - Inventory released successfully for order: {}", orderId);
        } catch (Exception e) {
            log.error("Saga step [RESERVE_INVENTORY] - Failed to release inventory for order: {}. " +
                    "Manual intervention may be required.", orderId, e);
        }
    }
}
