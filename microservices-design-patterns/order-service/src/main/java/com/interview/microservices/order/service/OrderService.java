package com.interview.microservices.order.service;

import com.interview.microservices.common.dto.ApiResponse;
import com.interview.microservices.common.dto.InventoryDto;
import com.interview.microservices.common.dto.OrderDto;
import com.interview.microservices.common.dto.ProductDto;
import com.interview.microservices.common.enums.OrderStatus;
import com.interview.microservices.common.event.OrderEvent;
import com.interview.microservices.order.client.InventoryFeignClient;
import com.interview.microservices.order.client.ProductFeignClient;
import com.interview.microservices.order.entity.Order;
import com.interview.microservices.order.entity.OrderItem;
import com.interview.microservices.order.event.OrderEventPublisher;
import com.interview.microservices.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Core order processing service demonstrating Resilience4j fault tolerance.
 *
 * ============================================================
 * CIRCUIT BREAKER PATTERN (Resilience4j)
 * ============================================================
 *
 * Problem:
 *   When product-service or inventory-service is down, order-service
 *   would wait for TCP timeout on every request, consuming threads
 *   and potentially cascading the failure to upstream callers.
 *
 * Solution:
 *   The circuit breaker monitors call outcomes in a sliding window.
 *   When the failure rate exceeds a threshold, it "opens" the circuit
 *   and immediately returns a fallback response without calling the
 *   downstream service. After a wait period, it enters "half-open"
 *   state and allows a limited number of test calls through.
 *
 * State Machine:
 *   CLOSED --> (failure rate >= 50%) --> OPEN
 *   OPEN   --> (wait 10s)           --> HALF_OPEN
 *   HALF_OPEN --> (test calls pass)  --> CLOSED
 *   HALF_OPEN --> (test calls fail)  --> OPEN
 *
 * Annotations used:
 *   @CircuitBreaker : Opens circuit on repeated failures
 *   @Retry          : Retries failed calls (max 3 attempts, 1s wait)
 *   @RateLimiter    : Limits call rate to protect downstream services
 *
 * Interview Tip:
 *   "The annotation order matters! Resilience4j applies decorators in
 *    this order: Retry -> CircuitBreaker -> RateLimiter -> TimeLimiter.
 *    So a single logical call may be retried 3 times, each attempt
 *    checked by the circuit breaker, and the overall rate limited."
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductFeignClient productFeignClient;
    private final InventoryFeignClient inventoryFeignClient;
    private final OrderEventPublisher orderEventPublisher;

    // ======================== CREATE ORDER ========================

    /**
     * Creates an order with circuit breaker, retry, and rate limiter protection.
     *
     * Flow:
     *   1. Validate each item: fetch product price from product-service
     *   2. Check inventory availability from inventory-service
     *   3. Calculate total, persist order, reserve inventory
     *   4. Publish OrderEvent to Kafka for downstream processing
     *
     * If product-service or inventory-service is down:
     *   - @Retry retries up to 3 times with 1s backoff
     *   - @CircuitBreaker opens after 50% failure rate in sliding window of 10
     *   - Fallback method createOrderFallback() returns a safe error response
     */
    @CircuitBreaker(name = "orderService", fallbackMethod = "createOrderFallback")
    @Retry(name = "orderService", fallbackMethod = "createOrderRetryFallback")
    @RateLimiter(name = "orderService")
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Creating order for userId={}", orderDto.getUserId());

        Order order = Order.builder()
                .userId(orderDto.getUserId())
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Process each order item: validate product and check inventory
        for (OrderDto.OrderItemDto itemDto : orderDto.getItems()) {

            // Call product-service (via Feign) to get current price
            ApiResponse<ProductDto> productResponse = productFeignClient.getProduct(itemDto.getProductId());
            ProductDto product = productResponse.getData();

            if (product == null) {
                throw new RuntimeException("Product not found: " + itemDto.getProductId());
            }

            // Call inventory-service (via Feign) to check availability
            ApiResponse<InventoryDto> inventoryResponse =
                    inventoryFeignClient.getInventory(itemDto.getProductId());
            InventoryDto inventory = inventoryResponse.getData();

            if (inventory == null || inventory.getAvailableQuantity() < itemDto.getQuantity()) {
                throw new RuntimeException("Insufficient inventory for product: " + itemDto.getProductId());
            }

            // Build order item with price snapshot
            BigDecimal unitPrice = product.getPrice();
            OrderItem orderItem = OrderItem.builder()
                    .productId(itemDto.getProductId())
                    .quantity(itemDto.getQuantity())
                    .unitPrice(unitPrice)
                    .build();

            order.addItem(orderItem);
            totalAmount = totalAmount.add(unitPrice.multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }

        order.setTotalAmount(totalAmount);

        // Persist order
        Order savedOrder = orderRepository.save(order);
        log.info("Order created: id={}, total={}", savedOrder.getId(), savedOrder.getTotalAmount());

        // Reserve inventory for each item
        for (OrderItem item : savedOrder.getItems()) {
            inventoryFeignClient.reserveInventory(item.getProductId(), item.getQuantity());
        }

        // Publish event to Kafka for downstream services (payment, saga, etc.)
        publishOrderEvent(savedOrder);

        return mapToDto(savedOrder);
    }

    /**
     * CIRCUIT BREAKER FALLBACK for createOrder.
     *
     * Called when the circuit is OPEN (downstream service is known to be down).
     * Returns a meaningful error without attempting the call.
     *
     * Interview Tip:
     *   "The fallback method must have the same return type and parameters
     *    as the original method, plus an additional Throwable parameter.
     *    It provides a graceful degradation instead of an exception."
     */
    public OrderDto createOrderFallback(OrderDto orderDto, Throwable throwable) {
        log.error("Circuit breaker OPEN - createOrder fallback triggered for userId={}: {}",
                orderDto.getUserId(), throwable.getMessage());

        return OrderDto.builder()
                .userId(orderDto.getUserId())
                .status(OrderStatus.CREATED)
                .build();
    }

    /**
     * RETRY FALLBACK for createOrder.
     *
     * Called after all retry attempts are exhausted (3 attempts with 1s backoff).
     */
    public OrderDto createOrderRetryFallback(OrderDto orderDto, Throwable throwable) {
        log.error("All retry attempts exhausted - createOrder retry fallback for userId={}: {}",
                orderDto.getUserId(), throwable.getMessage());

        return OrderDto.builder()
                .userId(orderDto.getUserId())
                .status(OrderStatus.CREATED)
                .build();
    }

    // ======================== GET ORDER BY ID ========================

    @CircuitBreaker(name = "orderService", fallbackMethod = "getOrderByIdFallback")
    @Retry(name = "orderService")
    public OrderDto getOrderById(Long id) {
        log.info("Fetching order: id={}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        return mapToDto(order);
    }

    public OrderDto getOrderByIdFallback(Long id, Throwable throwable) {
        log.error("Fallback - getOrderById for id={}: {}", id, throwable.getMessage());
        return OrderDto.builder().id(id).build();
    }

    // ======================== GET ORDERS BY USER ========================

    @CircuitBreaker(name = "orderService", fallbackMethod = "getOrdersByUserIdFallback")
    @Retry(name = "orderService")
    public List<OrderDto> getOrdersByUserId(Long userId) {
        log.info("Fetching orders for userId={}", userId);

        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByUserIdFallback(Long userId, Throwable throwable) {
        log.error("Fallback - getOrdersByUserId for userId={}: {}", userId, throwable.getMessage());
        return List.of();
    }

    // ======================== CANCEL ORDER ========================

    @CircuitBreaker(name = "orderService", fallbackMethod = "cancelOrderFallback")
    @Retry(name = "orderService")
    @Transactional
    public OrderDto cancelOrder(Long id) {
        log.info("Cancelling order: id={}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));

        if (order.getStatus() == OrderStatus.DELIVERED ||
                order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Cannot cancel order in status: " + order.getStatus());
        }

        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);

        // Release reserved inventory for each item
        for (OrderItem item : savedOrder.getItems()) {
            try {
                inventoryFeignClient.releaseInventory(item.getProductId(), item.getQuantity());
            } catch (Exception e) {
                log.warn("Failed to release inventory for product {}: {}",
                        item.getProductId(), e.getMessage());
                // In production, a compensating transaction or saga would handle this
            }
        }

        // Publish cancellation event
        publishOrderEvent(savedOrder);

        return mapToDto(savedOrder);
    }

    public OrderDto cancelOrderFallback(Long id, Throwable throwable) {
        log.error("Fallback - cancelOrder for id={}: {}", id, throwable.getMessage());
        return OrderDto.builder().id(id).build();
    }

    // ======================== HELPERS ========================

    private void publishOrderEvent(Order order) {
        OrderEvent event = OrderEvent.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .items(order.getItems().stream()
                        .map(item -> OrderEvent.OrderItemEvent.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .build())
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();

        orderEventPublisher.publishOrderEvent(event);
    }

    private OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream()
                        .map(item -> OrderDto.OrderItemDto.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .unitPrice(item.getUnitPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
