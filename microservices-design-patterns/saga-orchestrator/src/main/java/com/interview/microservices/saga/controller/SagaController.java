package com.interview.microservices.saga.controller;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.saga.choreography.ChoreographySagaManager;
import com.interview.microservices.saga.entity.SagaInstance;
import com.interview.microservices.saga.orchestration.SagaOrchestrator;
import com.interview.microservices.saga.repository.SagaInstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing endpoints to start and monitor sagas.
 *
 * Provides two saga initiation endpoints:
 * - POST /api/saga/orchestration/start: Starts an orchestration-based saga
 *   where a central SagaOrchestrator coordinates all steps synchronously.
 * - POST /api/saga/choreography/start: Starts a choreography-based saga
 *   where services communicate via Kafka events asynchronously.
 *
 * Also provides a status endpoint to query saga progress by ID.
 */
@Slf4j
@RestController
@RequestMapping("/api/saga")
@RequiredArgsConstructor
public class SagaController {

    private final SagaOrchestrator sagaOrchestrator;
    private final ChoreographySagaManager choreographySagaManager;
    private final SagaInstanceRepository sagaInstanceRepository;

    /**
     * Starts an orchestration-based saga for the given order.
     *
     * The orchestrator executes steps sequentially:
     *   RESERVE_INVENTORY -> PROCESS_PAYMENT -> CONFIRM_ORDER
     * and compensates on failure.
     *
     * @param orderId the order ID to process
     * @return the final saga state
     */
    @PostMapping("/orchestration/start")
    public ResponseEntity<ApiResponse<SagaInstance>> startOrchestrationSaga(
            @RequestParam Long orderId) {
        log.info("REST: Starting orchestration saga for order: {}", orderId);
        SagaInstance sagaInstance = sagaOrchestrator.executeSaga(orderId);
        return ResponseEntity.ok(ApiResponse.success("Orchestration saga executed", sagaInstance));
    }

    /**
     * Starts a choreography-based saga for the given order.
     *
     * Publishes an initial OrderEvent to Kafka and returns immediately.
     * The saga progresses asynchronously through event-driven communication
     * between services. Use the status endpoint to check progress.
     *
     * @param orderId the order ID to process
     * @return the initial saga state
     */
    @PostMapping("/choreography/start")
    public ResponseEntity<ApiResponse<SagaInstance>> startChoreographySaga(
            @RequestParam Long orderId) {
        log.info("REST: Starting choreography saga for order: {}", orderId);
        SagaInstance sagaInstance = choreographySagaManager.startSaga(orderId);
        return ResponseEntity.ok(ApiResponse.success("Choreography saga initiated", sagaInstance));
    }

    /**
     * Retrieves the current status of a saga by its ID.
     *
     * @param sagaId the saga instance ID
     * @return the saga instance with current status and step information
     */
    @GetMapping("/{sagaId}/status")
    public ResponseEntity<ApiResponse<SagaInstance>> getSagaStatus(
            @PathVariable Long sagaId) {
        log.info("REST: Fetching saga status for sagaId: {}", sagaId);
        return sagaInstanceRepository.findById(sagaId)
                .map(saga -> ResponseEntity.ok(ApiResponse.success(saga)))
                .orElse(ResponseEntity.notFound().build());
    }
}
