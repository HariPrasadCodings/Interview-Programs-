package com.interview.microservices.common.enums;

public enum SagaStatus {
    STARTED,
    INVENTORY_RESERVED,
    PAYMENT_PROCESSED,
    ORDER_CONFIRMED,
    COMPENSATING,
    COMPENSATED,
    FAILED
}
