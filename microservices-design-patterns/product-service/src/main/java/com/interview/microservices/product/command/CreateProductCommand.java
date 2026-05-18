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
 * Command object that encapsulates the intent to create a new product.
 *
 * <p>In CQRS the <em>command</em> side only accepts operations that mutate
 * state. Validation annotations ensure the command is well-formed before
 * it reaches the service layer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private String category;

    @PositiveOrZero(message = "Quantity must be zero or positive")
    @Builder.Default
    private Integer quantity = 0;
}
