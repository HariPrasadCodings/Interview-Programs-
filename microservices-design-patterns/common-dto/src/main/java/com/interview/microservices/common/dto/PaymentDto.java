package com.interview.microservices.common.dto;

import com.interview.microservices.common.enums.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;

    @NotNull(message = "Order ID is required")
    private Long orderId;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    private PaymentStatus status;
    private String transactionId;
    private LocalDateTime processedAt;
}
