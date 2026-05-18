package com.interview.microservices.common.enums;

public enum OrderStatus {
    CREATED,
    INVENTORY_RESERVED,
    INVENTORY_FAILED,
    PAYMENT_PROCESSING,
    PAYMENT_COMPLETED,
    PAYMENT_FAILED,
    CONFIRMED,
    SHIPPING,
    DELIVERED,
    CANCELLED,
    ROLLBACK
}
