package com.interview.microservices.saga.repository;

import com.interview.microservices.saga.entity.SagaInstance;
import com.interview.microservices.common.enums.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for managing {@link SagaInstance} entities.
 *
 * Provides finder methods for querying sagas by order ID or status,
 * which is useful for monitoring, recovery, and debugging saga flows.
 */
@Repository
public interface SagaInstanceRepository extends JpaRepository<SagaInstance, Long> {

    Optional<SagaInstance> findByOrderId(Long orderId);

    List<SagaInstance> findByStatus(SagaStatus status);
}
