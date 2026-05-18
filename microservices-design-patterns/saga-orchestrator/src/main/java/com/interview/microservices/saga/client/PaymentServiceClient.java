package com.interview.microservices.saga.client;

import com.interview.microservices.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Feign client for communicating with the Payment Service.
 *
 * Used by the orchestration-based saga to process and refund payments.
 * Payment processing is the forward action; refund is the compensating action.
 *
 * The service name "payment-service" is resolved via Eureka service discovery.
 */
@FeignClient(name = "payment-service")
public interface PaymentServiceClient {

    @PostMapping("/api/payments/process/{orderId}")
    ApiResponse<?> processPayment(@PathVariable("orderId") Long orderId);

    @PostMapping("/api/payments/refund/{orderId}")
    ApiResponse<?> refundPayment(@PathVariable("orderId") Long orderId);
}
