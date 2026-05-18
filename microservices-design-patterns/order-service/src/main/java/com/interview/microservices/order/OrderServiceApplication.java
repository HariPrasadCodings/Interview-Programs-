package com.interview.microservices.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Order Service - Entry Point
 *
 * Demonstrates two major microservices patterns:
 *
 * 1. SERVICE COMMUNICATION (3 approaches):
 *    - Feign Client   : Declarative, annotation-driven REST client (@FeignClient)
 *    - RestTemplate    : Traditional imperative HTTP client (with @LoadBalanced)
 *    - WebClient       : Reactive, non-blocking HTTP client (with @LoadBalanced)
 *
 * 2. CIRCUIT BREAKER (Resilience4j):
 *    - @CircuitBreaker : Prevents cascading failures when downstream services are down
 *    - @Retry          : Automatic retry with configurable attempts and backoff
 *    - @RateLimiter    : Limits throughput to protect downstream services
 *
 * Interview Tip:
 *   "@EnableFeignClients scans for interfaces annotated with @FeignClient
 *    and creates proxy implementations at runtime. It integrates with
 *    Eureka for service discovery and Resilience4j for fault tolerance."
 */
@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
