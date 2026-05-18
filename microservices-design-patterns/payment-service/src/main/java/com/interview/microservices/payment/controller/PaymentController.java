package com.interview.microservices.payment.controller;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.PaymentDto;
import com.interview.microservices.payment.entity.Payment;
import com.interview.microservices.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing payment operations.
 *
 * <p>Endpoints:
 * <ul>
 *   <li>POST /api/payments/process       - process a new payment</li>
 *   <li>POST /api/payments/refund/{id}   - refund a payment (saga compensation)</li>
 *   <li>GET  /api/payments/order/{id}    - look up payment by order ID</li>
 * </ul>
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Process a payment for an order.
     *
     * @param paymentDto contains orderId and amount
     * @return the processed payment details
     */
    @PostMapping("/process")
    public ResponseEntity<ApiResponse<PaymentDto>> processPayment(
            @Valid @RequestBody PaymentDto paymentDto) {
        log.info("POST /api/payments/process - orderId={}, amount={}",
                paymentDto.getOrderId(), paymentDto.getAmount());

        Payment payment = paymentService.processPayment(
                paymentDto.getOrderId(), paymentDto.getAmount());

        PaymentDto responseDto = toDto(payment);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Payment processed successfully", responseDto));
    }

    /**
     * Refund a payment by order ID (used for saga compensation).
     *
     * @param orderId the order whose payment should be refunded
     * @return the refunded payment details
     */
    @PostMapping("/refund/{orderId}")
    public ResponseEntity<ApiResponse<PaymentDto>> refundPayment(
            @PathVariable Long orderId) {
        log.info("POST /api/payments/refund/{}", orderId);

        Payment payment = paymentService.refundPayment(orderId);

        PaymentDto responseDto = toDto(payment);

        return ResponseEntity.ok(
                ApiResponse.success("Payment refunded successfully", responseDto));
    }

    /**
     * Get payment details by order ID.
     *
     * @param orderId the order identifier
     * @return the payment details
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<PaymentDto>> getPaymentByOrderId(
            @PathVariable Long orderId) {
        log.info("GET /api/payments/order/{}", orderId);

        Payment payment = paymentService.getPaymentByOrderId(orderId);

        PaymentDto responseDto = toDto(payment);

        return ResponseEntity.ok(ApiResponse.success(responseDto));
    }

    // -----------------------------------------------------------------------
    // Private helper
    // -----------------------------------------------------------------------

    /**
     * Map a {@link Payment} entity to a {@link PaymentDto}.
     */
    private PaymentDto toDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .processedAt(payment.getProcessedAt())
                .build();
    }
}
