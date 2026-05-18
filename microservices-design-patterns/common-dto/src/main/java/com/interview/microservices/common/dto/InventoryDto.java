package com.interview.microservices.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Long id;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer availableQuantity;

    private Integer reservedQuantity;
}
