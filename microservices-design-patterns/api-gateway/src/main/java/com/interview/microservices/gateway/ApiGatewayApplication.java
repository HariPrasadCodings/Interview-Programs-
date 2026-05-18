package com.interview.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * API Gateway - Single entry point for all client requests.
 *
 * <p>This application uses <b>Spring Cloud Gateway</b> (reactive, WebFlux-based)
 * to route incoming HTTP requests to downstream microservices discovered via
 * Eureka. Key responsibilities handled at the gateway layer:</p>
 *
 * <ul>
 *   <li><b>Routing</b> - path-based routing to auth, product, order, payment,
 *       and inventory services using {@code lb://} (load-balanced) URIs.</li>
 *   <li><b>Authentication</b> - JWT token validation via a custom
 *       {@code AuthenticationFilter} applied to protected routes.</li>
 *   <li><b>Rate Limiting</b> - Redis-backed token-bucket rate limiter
 *       configured per route.</li>
 *   <li><b>Circuit Breaking</b> - Resilience4j circuit breaker on each
 *       route to prevent cascading failures.</li>
 *   <li><b>Logging</b> - Global pre/post logging filter for observability.</li>
 * </ul>
 *
 * <p><b>Design Pattern:</b> API Gateway Pattern - centralises cross-cutting
 * concerns (auth, rate limiting, logging) so individual services stay focused
 * on business logic.</p>
 *
 * @see com.interview.microservices.gateway.filter.AuthenticationFilter
 * @see com.interview.microservices.gateway.filter.LoggingFilter
 * @see com.interview.microservices.gateway.config.RateLimiterConfig
 */
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
