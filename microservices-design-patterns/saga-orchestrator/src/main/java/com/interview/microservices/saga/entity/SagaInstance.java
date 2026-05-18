package com.interview.microservices.saga.entity;

import com.interview.microservices.common.enums.SagaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * JPA entity that tracks the state of a saga execution.
 *
 * Each saga instance records:
 * - Which order it belongs to
 * - The current step in the saga workflow
 * - The overall status (STARTED, INVENTORY_RESERVED, PAYMENT_PROCESSED,
 *   ORDER_CONFIRMED, COMPENSATING, COMPENSATED, FAILED)
 * - Timestamps for auditing
 *
 * This entity is persisted in the saga-orchestrator's own database,
 * following the Database-per-Service pattern. It provides visibility
 * into saga progress and enables recovery after crashes.
 */
@Entity
@Table(name = "saga_instances")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SagaInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "current_step", nullable = false)
    private String currentStep;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SagaStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
