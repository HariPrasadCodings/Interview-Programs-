package com.interview.microservices.payment.repository;

import com.interview.microservices.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link Payment} entities.
 *
 * <p>Operates against the payment-service's dedicated database,
 * reinforcing the Database-per-Service pattern.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Find a payment by the associated order ID.
     *
     * @param orderId the order identifier
     * @return an Optional containing the payment if found
     */
    Optional<Payment> findByOrderId(Long orderId);

    /**
     * Find a payment by its unique transaction ID.
     *
     * @param transactionId the external transaction reference
     * @return an Optional containing the payment if found
     */
    Optional<Payment> findByTransactionId(String transactionId);
}
