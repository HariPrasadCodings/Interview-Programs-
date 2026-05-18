package com.interview.microservices.saga.orchestration;

import com.interview.microservices.common.enums.SagaStatus;
import com.interview.microservices.saga.entity.SagaInstance;
import com.interview.microservices.saga.orchestration.steps.InventoryReserveStep;
import com.interview.microservices.saga.orchestration.steps.OrderConfirmStep;
import com.interview.microservices.saga.orchestration.steps.PaymentProcessStep;
import com.interview.microservices.saga.repository.SagaInstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Central Saga Orchestrator that coordinates a distributed transaction
 * across multiple microservices using the Orchestration approach.
 *
 * Saga Flow (happy path):
 *   CREATE_ORDER -> RESERVE_INVENTORY -> PROCESS_PAYMENT -> CONFIRM_ORDER
 *
 * On failure at any step, the orchestrator compensates all previously
 * completed steps in reverse order:
 *   e.g., if PROCESS_PAYMENT fails:
 *     compensate RESERVE_INVENTORY (release stock)
 *
 * Interview notes:
 * - Orchestration vs Choreography:
 *   - Orchestration: Central coordinator (this class) tells each service
 *     what to do. Easier to understand and debug. Single point of failure.
 *   - Choreography: No central coordinator. Each service publishes events
 *     and reacts to others. More decoupled but harder to trace.
 * - The saga pattern replaces distributed transactions (2PC) in
 *   microservices, trading strong consistency for availability and
 *   partition tolerance (CAP theorem).
 * - Each step MUST be idempotent for safe retries.
 * - Compensating transactions must be commutative (order shouldn't matter
 *   for the final state).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SagaOrchestrator {

    private final InventoryReserveStep inventoryReserveStep;
    private final PaymentProcessStep paymentProcessStep;
    private final OrderConfirmStep orderConfirmStep;
    private final SagaInstanceRepository sagaInstanceRepository;

    /**
     * Executes the saga for the given order ID.
     *
     * Steps are executed sequentially. If any step fails, all previously
     * completed steps are compensated in reverse order.
     *
     * @param orderId the ID of the order to process
     * @return the SagaInstance reflecting the final state
     */
    public SagaInstance executeSaga(Long orderId) {
        log.info("Starting orchestration saga for order: {}", orderId);

        // Create saga instance to track progress
        SagaInstance sagaInstance = SagaInstance.builder()
                .orderId(orderId)
                .currentStep("CREATE_ORDER")
                .status(SagaStatus.STARTED)
                .build();
        sagaInstance = sagaInstanceRepository.save(sagaInstance);

        // Define the saga steps in execution order
        List<SagaStep> steps = List.of(
                inventoryReserveStep,
                paymentProcessStep,
                orderConfirmStep
        );

        // Track completed steps for potential compensation
        List<SagaStep> completedSteps = new ArrayList<>();

        for (SagaStep step : steps) {
            sagaInstance.setCurrentStep(step.getName());
            sagaInstanceRepository.save(sagaInstance);

            log.info("Executing saga step: {} for order: {}", step.getName(), orderId);

            boolean success = step.execute(orderId);

            if (!success) {
                log.warn("Saga step {} failed for order: {}. Starting compensation...",
                        step.getName(), orderId);

                // Mark saga as compensating
                sagaInstance.setStatus(SagaStatus.COMPENSATING);
                sagaInstanceRepository.save(sagaInstance);

                // Compensate completed steps in reverse order
                compensate(completedSteps, orderId);

                // Mark saga as compensated or failed
                sagaInstance.setStatus(SagaStatus.COMPENSATED);
                sagaInstance.setCurrentStep(step.getName() + "_FAILED");
                sagaInstanceRepository.save(sagaInstance);

                log.info("Saga compensation completed for order: {}", orderId);
                return sagaInstance;
            }

            completedSteps.add(step);
            updateSagaStatus(sagaInstance, step.getName());
        }

        // All steps succeeded
        sagaInstance.setStatus(SagaStatus.ORDER_CONFIRMED);
        sagaInstance.setCurrentStep("COMPLETED");
        sagaInstanceRepository.save(sagaInstance);

        log.info("Orchestration saga completed successfully for order: {}", orderId);
        return sagaInstance;
    }

    /**
     * Compensates completed steps in reverse order.
     * Each compensation is attempted even if a prior compensation fails,
     * to maximize rollback coverage. Failures are logged for manual review.
     */
    private void compensate(List<SagaStep> completedSteps, Long orderId) {
        log.info("Compensating {} completed steps for order: {}", completedSteps.size(), orderId);
        for (int i = completedSteps.size() - 1; i >= 0; i--) {
            SagaStep step = completedSteps.get(i);
            log.info("Compensating step: {} for order: {}", step.getName(), orderId);
            try {
                step.compensate(orderId);
            } catch (Exception e) {
                log.error("Compensation failed for step: {} and order: {}. Manual intervention required.",
                        step.getName(), orderId, e);
            }
        }
    }

    /**
     * Updates the saga status based on the completed step name.
     */
    private void updateSagaStatus(SagaInstance sagaInstance, String stepName) {
        SagaStatus newStatus = switch (stepName) {
            case "RESERVE_INVENTORY" -> SagaStatus.INVENTORY_RESERVED;
            case "PROCESS_PAYMENT" -> SagaStatus.PAYMENT_PROCESSED;
            case "CONFIRM_ORDER" -> SagaStatus.ORDER_CONFIRMED;
            default -> sagaInstance.getStatus();
        };
        sagaInstance.setStatus(newStatus);
        sagaInstanceRepository.save(sagaInstance);
    }
}
