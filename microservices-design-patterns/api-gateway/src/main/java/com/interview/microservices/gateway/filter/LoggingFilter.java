package com.interview.microservices.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Global gateway filter that logs request and response details for every
 * request that passes through the API Gateway.
 *
 * <h3>Logged information</h3>
 * <ul>
 *   <li><b>Pre-filter (request):</b> HTTP method, URI, remote address,
 *       and a unique request ID.</li>
 *   <li><b>Post-filter (response):</b> HTTP status code and elapsed time
 *       in milliseconds.</li>
 * </ul>
 *
 * <p>The filter is ordered with {@link Ordered#HIGHEST_PRECEDENCE} so that
 * it runs <em>before</em> all other filters on the way in, and
 * <em>after</em> all other filters on the way out - giving the most
 * accurate timing measurement.</p>
 *
 * <p><b>Interview note:</b> {@link GlobalFilter} applies to <em>every</em>
 * route automatically, whereas a named filter (like
 * {@link AuthenticationFilter}) must be explicitly added to a route
 * definition.</p>
 */
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // --- Pre-filter: log incoming request details ---
        String requestId = request.getId();
        HttpMethod method = request.getMethod();
        String uri = request.getURI().toString();
        String remoteAddress = request.getRemoteAddress() != null
                ? request.getRemoteAddress().getAddress().getHostAddress()
                : "unknown";

        long startTime = Instant.now().toEpochMilli();

        log.info("Incoming request  | id={} | method={} | uri={} | remoteAddr={}",
                requestId, method, uri, remoteAddress);

        // --- Post-filter: log response details after downstream processing ---
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            long elapsedMs = Instant.now().toEpochMilli() - startTime;

            log.info("Outgoing response | id={} | status={} | elapsed={}ms",
                    requestId,
                    response.getStatusCode(),
                    elapsedMs);
        }));
    }

    /**
     * Highest precedence ensures this filter wraps all others, giving the
     * most accurate latency measurement.
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
