package com.interview.microservices.saga.orchestration.steps;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.saga.client.PaymentServiceClient;
import com.interview.microservices.saga.orchestration.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Saga step that processes payment for an order.
 *
 * Forward action: Calls payment-service to charge the customer.
 * This step runs after inventory has been reserved, ensuring we
 * don't charge for items that aren't available.
 *
 * Compensating action: Calls payment-service to refund the payment.
 * If payment processing fails, the saga also compensates the prior
 * inventory reservation step.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentProcessStep implements SagaStep {

    private final PaymentServiceClient paymentServiceClient;

    @Override
    public String getName() {
        return "PROCESS_PAYMENT";
    }

    @Override
    public boolean execute(Long orderId) {
        log.info("Saga step [PROCESS_PAYMENT] - Processing payment for order: {}", orderId);
        try {
            ApiResponse<?> response = paymentServiceClient.processPayment(orderId);
            if (response.isSuccess()) {
                log.info("Saga step [PROCESS_PAYMENT] - Payment processed successfully for order: {}", orderId);
                return true;
            } else {
                log.warn("Saga step [PROCESS_PAYMENT] - Payment failed for order: {}. Reason: {}",
                        orderId, response.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.error("Saga step [PROCESS_PAYMENT] - Exception while processing payment for order: {}", orderId, e);
            return false;
        }
    }

    @Override
    public void compensate(Long orderId) {
        log.info("Saga step [PROCESS_PAYMENT] - Compensating: refunding payment for order: {}", orderId);
        try {
            paymentServiceClient.refundPayment(orderId);
            log.info("Saga step [PROCESS_PAYMENT] - Payment refunded successfully for order: {}", orderId);
        } catch (Exception e) {
            log.error("Saga step [PROCESS_PAYMENT] - Failed to refund payment for order: {}. " +
                    "Manual intervention may be required.", orderId, e);
        }
    }
}
