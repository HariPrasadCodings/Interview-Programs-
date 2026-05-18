package com.interview.microservices.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity representing the inventory (stock) for a single product.
 *
 * <p>Each product has two quantity fields:
 * <ul>
 *   <li>{@code availableQuantity} - stock that is free for new orders</li>
 *   <li>{@code reservedQuantity}  - stock that has been reserved by an
 *       in-progress order (part of the Saga pattern)</li>
 * </ul>
 *
 * <p>The total physical stock on hand equals
 * {@code availableQuantity + reservedQuantity}.
 */
@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long productId;

    @Column(nullable = false)
    @Builder.Default
    private Integer availableQuantity = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer reservedQuantity = 0;
}
