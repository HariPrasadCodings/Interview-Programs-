package com.interview.microservices.product.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Command object that encapsulates the intent to update an existing product.
 *
 * <p>The {@code id} identifies the product to be modified. All fields are
 * required so that a full replacement is performed on the write model; the
 * resulting domain event then propagates the changes to the read model.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductCommand {

    @NotNull(message = "Product ID is required")
    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String category;

    @PositiveOrZero(message = "Quantity must be zero or positive")
    private Integer quantity;
}
