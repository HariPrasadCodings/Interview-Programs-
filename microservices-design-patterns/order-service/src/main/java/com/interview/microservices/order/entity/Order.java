package com.interview.microservices.order.entity;

import com.interview.microservices.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order aggregate root entity.
 *
 * Interview Tip:
 *   "Each microservice owns its database (Database-per-Service pattern).
 *    The Order entity lives exclusively in the order-service's H2 database.
 *    Other services access order data only through the Order Service API,
 *    never by querying this table directly."
 */
@Entity
@Table(name = "orders") // "order" is a SQL reserved word
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OrderStatus status;

    /**
     * One-to-many relationship with OrderItem.
     * CascadeType.ALL ensures items are persisted/removed with the order.
     * orphanRemoval=true deletes items that are removed from the list.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * Helper method to maintain bidirectional relationship.
     */
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}
