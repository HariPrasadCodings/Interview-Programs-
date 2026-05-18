package com.interview.microservices.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for WebClient with client-side load balancing.
 *
 * Interview Tip:
 *   "We annotate WebClient.Builder (not WebClient) with @LoadBalanced
 *    because WebClient instances are immutable once built. The builder
 *    receives a LoadBalancerExchangeFilterFunction that resolves service
 *    names via Eureka on every request."
 *
 * Key difference from RestTemplate:
 *   - RestTemplate uses an interceptor (synchronous)
 *   - WebClient uses an ExchangeFilterFunction (reactive/non-blocking)
 *   Both achieve the same goal: service-name -> IP:port resolution via Eureka.
 */
@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
