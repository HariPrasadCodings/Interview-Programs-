package com.interview.microservices.common.dto;

import com.interview.microservices.common.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    private List<OrderItemDto> items;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemDto {
        private Long productId;

        @Positive
        private Integer quantity;

        private BigDecimal unitPrice;
    }
}
