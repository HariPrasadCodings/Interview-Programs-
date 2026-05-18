package com.interview.microservices.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for RestTemplate with client-side load balancing.
 *
 * Interview Tip:
 *   "@LoadBalanced registers a LoadBalancerInterceptor that intercepts
 *    every outgoing request. When the URL host is a service name
 *    (e.g., 'product-service'), the interceptor resolves it to an
 *    actual IP:port using Eureka and applies round-robin load balancing
 *    across all registered instances."
 *
 * Without @LoadBalanced:
 *   restTemplate.getForObject("http://product-service/api/products/1", ...)
 *   --> UnknownHostException: product-service
 *
 * With @LoadBalanced:
 *   restTemplate.getForObject("http://product-service/api/products/1", ...)
 *   --> HTTP GET http://192.168.1.10:8082/api/products/1  (resolved via Eureka)
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
