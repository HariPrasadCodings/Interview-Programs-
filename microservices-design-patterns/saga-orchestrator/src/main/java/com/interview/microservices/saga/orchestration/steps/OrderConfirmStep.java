package com.interview.microservices.saga.orchestration.steps;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.saga.client.OrderServiceClient;
import com.interview.microservices.saga.orchestration.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Saga step that confirms the order after inventory reservation
 * and payment processing have both succeeded.
 *
 * Forward action: Calls order-service to update order status to CONFIRMED.
 * This is the final step in the saga's happy path.
 *
 * Compensating action: Calls order-service to cancel/rollback the order.
 * Although this is the last step, compensation is still needed if a
 * post-confirmation step were added in the future, or if the confirm
 * call itself fails partially.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConfirmStep implements SagaStep {

    private final OrderServiceClient orderServiceClient;

    @Override
    public String getName() {
        return "CONFIRM_ORDER";
    }

    @Override
    public boolean execute(Long orderId) {
        log.info("Saga step [CONFIRM_ORDER] - Confirming order: {}", orderId);
        try {
            ApiResponse<?> response = orderServiceClient.confirmOrder(orderId);
            if (response.isSuccess()) {
                log.info("Saga step [CONFIRM_ORDER] - Order confirmed successfully: {}", orderId);
                return true;
            } else {
                log.warn("Saga step [CONFIRM_ORDER] - Failed to confirm order: {}. Reason: {}",
                        orderId, response.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.error("Saga step [CONFIRM_ORDER] - Exception while confirming order: {}", orderId, e);
            return false;
        }
    }

    @Override
    public void compensate(Long orderId) {
        log.info("Saga step [CONFIRM_ORDER] - Compensating: cancelling order: {}", orderId);
        try {
            orderServiceClient.cancelOrder(orderId);
            log.info("Saga step [CONFIRM_ORDER] - Order cancelled successfully: {}", orderId);
        } catch (Exception e) {
            log.error("Saga step [CONFIRM_ORDER] - Failed to cancel order: {}. " +
                    "Manual intervention may be required.", orderId, e);
        }
    }
}
