package com.interview.microservices.saga.orchestration;

/**
 * Interface representing a single step in an orchestration-based saga.
 *
 * Each step has two operations:
 * - {@link #execute(Long)}: The forward action (e.g., reserve inventory,
 *   process payment). Returns true on success, false on failure.
 * - {@link #compensate(Long)}: The compensating action that undoes the
 *   forward action (e.g., release inventory, refund payment). Called
 *   during rollback when a subsequent step fails.
 *
 * Interview notes:
 * - Saga steps must be idempotent to handle retries safely.
 * - Compensating transactions are the key difference between Sagas
 *   and traditional 2PC (two-phase commit) distributed transactions.
 * - Unlike 2PC, sagas guarantee eventual consistency, not ACID.
 */
public interface SagaStep {

    /**
     * Returns the name of this step for logging and persistence.
     */
    String getName();

    /**
     * Executes the forward action for this saga step.
     *
     * @param orderId the order ID this saga is processing
     * @return true if the step succeeded, false if it failed
     */
    boolean execute(Long orderId);

    /**
     * Executes the compensating (rollback) action for this saga step.
     * Called when a subsequent step fails and the saga must undo
     * previously completed steps in reverse order.
     *
     * @param orderId the order ID this saga is processing
     */
    void compensate(Long orderId);
}
