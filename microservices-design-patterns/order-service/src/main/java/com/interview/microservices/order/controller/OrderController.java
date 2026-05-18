package com.interview.microservices.order.controller;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.OrderDto;
import com.interview.microservices.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for order management.
 *
 * Endpoints:
 *   POST   /api/orders              - Create a new order
 *   GET    /api/orders/{id}         - Get order by ID
 *   GET    /api/orders/user/{userId} - Get all orders for a user
 *   PUT    /api/orders/{id}/cancel  - Cancel an order
 *
 * Interview Tip:
 *   "The controller is a thin layer that delegates to OrderService.
 *    All business logic, circuit breaker annotations, and inter-service
 *    communication live in the service layer. The controller only handles
 *    HTTP concerns: request validation, status codes, and response wrapping."
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates a new order.
     *
     * Request body example:
     * {
     *   "userId": 1,
     *   "items": [
     *     { "productId": 101, "quantity": 2 },
     *     { "productId": 102, "quantity": 1 }
     *   ]
     * }
     *
     * Flow:
     *   Controller -> OrderService -> ProductFeignClient (get prices)
     *                              -> InventoryFeignClient (check & reserve)
     *                              -> OrderRepository (persist)
     *                              -> OrderEventPublisher (Kafka)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<OrderDto>> createOrder(@Valid @RequestBody OrderDto orderDto) {
        log.info("POST /api/orders - Creating order for userId={}", orderDto.getUserId());
        OrderDto created = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order created successfully", created));
    }

    /**
     * Retrieves an order by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDto>> getOrderById(@PathVariable Long id) {
        log.info("GET /api/orders/{}", id);
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(ApiResponse.success(order));
    }

    /**
     * Retrieves all orders for a given user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getOrdersByUserId(@PathVariable Long userId) {
        log.info("GET /api/orders/user/{}", userId);
        List<OrderDto> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(orders));
    }

    /**
     * Cancels an order and releases reserved inventory.
     *
     * Flow:
     *   Controller -> OrderService -> OrderRepository (update status)
     *                              -> InventoryFeignClient (release inventory)
     *                              -> OrderEventPublisher (Kafka: CANCELLED event)
     */
    @PutMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<OrderDto>> cancelOrder(@PathVariable Long id) {
        log.info("PUT /api/orders/{}/cancel", id);
        OrderDto cancelled = orderService.cancelOrder(id);
        return ResponseEntity.ok(ApiResponse.success("Order cancelled successfully", cancelled));
    }
}
