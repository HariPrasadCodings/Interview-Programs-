package com.interview.microservices.order.repository;

import com.interview.microservices.common.enums.OrderStatus;
import com.interview.microservices.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Order aggregate root.
 *
 * Interview Tip:
 *   "Spring Data JPA derives query implementations from method names
 *    at startup. 'findByUserId' translates to
 *    SELECT * FROM orders WHERE user_id = ?1."
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

    List<Order> findByStatus(OrderStatus status);
}
