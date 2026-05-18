package com.interview.microservices.product.readmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * MongoDB document representing a product in the <b>read model</b>.
 *
 * <p>This is a denormalised projection optimised for fast queries.
 * It is kept in sync with the write model (H2) via Kafka events consumed
 * by {@link com.interview.microservices.product.event.ProductEventHandler}.
 *
 * <p><b>CQRS insight:</b> the read model can have a different schema from
 * the write model. Fields can be flattened, pre-computed, or aggregated to
 * match specific query patterns without affecting the write side.
 */
@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReadModel {

    /**
     * Uses the same identifier as the write-side {@code Product.id}
     * stored as a String for MongoDB's {@code _id} field.
     */
    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    @Indexed
    private String category;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
