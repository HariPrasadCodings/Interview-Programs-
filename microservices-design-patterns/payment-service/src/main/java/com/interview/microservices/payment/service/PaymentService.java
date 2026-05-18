package com.interview.microservices.payment.service;

import com.interview.microservices.common.enums.PaymentStatus;
import com.interview.microservices.common.event.PaymentEvent;
import com.interview.microservices.payment.entity.Payment;
import com.interview.microservices.payment.event.PaymentEventPublisher;
import com.interview.microservices.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Core payment processing service.
 *
 * <p>Handles payment processing, refunds (saga compensation), and
 * publishes {@link PaymentEvent} messages to Kafka after each operation
 * so downstream services can react asynchronously.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventPublisher paymentEventPublisher;

    /**
     * Process a payment for the given order.
     *
     * <p>Simulates payment gateway interaction: generates a transaction ID,
     * marks the payment as COMPLETED, persists it, and publishes a
     * {@link PaymentEvent} to Kafka.
     *
     * @param orderId the order to process payment for
     * @param amount  the payment amount
     * @return the persisted Payment entity
     */
    @Transactional
    public Payment processPayment(Long orderId, BigDecimal amount) {
        log.info("Processing payment for orderId={}, amount={}", orderId, amount);

        // Check for duplicate payment
        if (paymentRepository.findByOrderId(orderId).isPresent()) {
            throw new IllegalStateException(
                    "Payment already exists for orderId=" + orderId);
        }

        // Simulate payment gateway processing
        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        PaymentStatus status = simulatePaymentGateway(amount);

        Payment payment = Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .status(status)
                .transactionId(transactionId)
                .processedAt(LocalDateTime.now())
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment saved: id={}, transactionId={}, status={}",
                savedPayment.getId(), transactionId, status);

        // Publish event to Kafka for saga choreography / notifications
        publishEvent(savedPayment);

        return savedPayment;
    }

    /**
     * Refund a payment for the given order (saga compensation).
     *
     * <p>Used when a downstream step in the saga fails and the payment
     * must be rolled back. Updates the status to REFUNDED and publishes
     * a compensating {@link PaymentEvent}.
     *
     * @param orderId the order whose payment should be refunded
     * @return the updated Payment entity
     */
    @Transactional
    public Payment refundPayment(Long orderId) {
        log.info("Processing refund for orderId={}", orderId);

        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No payment found for orderId=" + orderId));

        if (payment.getStatus() == PaymentStatus.REFUNDED) {
            log.warn("Payment for orderId={} is already refunded", orderId);
            return payment;
        }

        payment.setStatus(PaymentStatus.REFUNDED);
        payment.setProcessedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment refunded: id={}, orderId={}", savedPayment.getId(), orderId);

        // Publish compensating event
        publishEvent(savedPayment);

        return savedPayment;
    }

    /**
     * Retrieve the payment associated with the given order.
     *
     * @param orderId the order identifier
     * @return the Payment entity
     */
    @Transactional(readOnly = true)
    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No payment found for orderId=" + orderId));
    }

    // -----------------------------------------------------------------------
    // Private helpers
    // -----------------------------------------------------------------------

    /**
     * Simulate a payment gateway call.
     *
     * <p>In a real system this would call Stripe, PayPal, etc. Here we
     * simply approve all payments with a positive amount.
     */
    private PaymentStatus simulatePaymentGateway(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("Payment declined: amount must be positive (was {})", amount);
            return PaymentStatus.FAILED;
        }
        // Simulate occasional failures for testing (amounts ending in .99)
        if (amount.stripTrailingZeros().toPlainString().endsWith(".99")) {
            log.warn("Simulated payment failure for amount={}", amount);
            return PaymentStatus.FAILED;
        }
        return PaymentStatus.COMPLETED;
    }

    /**
     * Build and publish a {@link PaymentEvent} from the given entity.
     */
    private void publishEvent(Payment payment) {
        PaymentEvent event = PaymentEvent.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .timestamp(LocalDateTime.now())
                .build();

        paymentEventPublisher.publish(event);
    }
}
