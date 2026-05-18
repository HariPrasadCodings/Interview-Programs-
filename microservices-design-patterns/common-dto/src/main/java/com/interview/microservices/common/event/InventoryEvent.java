package com.interview.microservices.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEvent {
    private Long orderId;
    private Map<Long, Integer> productQuantities; // productId -> quantity
    private String status; // RESERVED, RELEASED, INSUFFICIENT
    private LocalDateTime timestamp;
}
