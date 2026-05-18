package com.interview.microservices.gateway.filter;

import com.interview.microservices.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

/**
 * Custom Gateway filter that validates JWT tokens on protected routes.
 *
 * <h3>How it works</h3>
 * <ol>
 *   <li>The filter checks whether the incoming request path matches any of
 *       the configured <em>open endpoints</em> (e.g. {@code /api/auth/**}).
 *       If it does, the request is forwarded without authentication.</li>
 *   <li>For all other requests the filter looks for an {@code Authorization}
 *       header with a {@code Bearer} prefix.</li>
 *   <li>The JWT token is validated using {@link JwtUtil}. On success the
 *       filter extracts the {@code sub} (subject / username) and {@code role}
 *       claims and propagates them as {@code X-Auth-User} and
 *       {@code X-Auth-Role} headers to downstream services.</li>
 *   <li>If the token is missing, malformed, or expired the filter short-
 *       circuits with HTTP 401 Unauthorized.</li>
 * </ol>
 *
 * <h3>Usage in route configuration (application.yml)</h3>
 * <pre>
 * filters:
 *   - AuthenticationFilter
 * </pre>
 *
 * <p>Because the class extends {@link AbstractGatewayFilterFactory} and is
 * named {@code AuthenticationFilter}, Spring Cloud Gateway automatically
 * registers it under the name {@code AuthenticationFilter} (the suffix
 * {@code GatewayFilterFactory} is not required when the convention-based
 * short name is used).</p>
 *
 * @see JwtUtil
 */
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    private final JwtUtil jwtUtil;

    /**
     * Open (public) API paths that do not require a Bearer token.
     * Ant-style patterns are matched manually with a simple prefix check;
     * for production use consider Spring's {@code AntPathMatcher}.
     */
    private static final List<String> OPEN_ENDPOINTS = List.of(
            "/api/auth/",
            "/eureka",
            "/actuator"
    );

    public AuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    // ------------------------------------------------------------------ //
    //  Filter logic
    // ------------------------------------------------------------------ //

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            // 1. Skip authentication for open/public endpoints
            if (isOpenEndpoint(path)) {
                log.debug("Open endpoint accessed - skipping authentication: {}", path);
                return chain.filter(exchange);
            }

            // 2. Check for Authorization header
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                log.warn("Missing Authorization header for path: {}", path);
                return onUnauthorized(exchange, "Missing Authorization header");
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("Invalid Authorization header format for path: {}", path);
                return onUnauthorized(exchange, "Invalid Authorization header format");
            }

            // 3. Extract and validate the token
            String token = authHeader.substring(7);
            try {
                jwtUtil.validateToken(token);
            } catch (Exception e) {
                log.warn("JWT validation failed for path {}: {}", path, e.getMessage());
                return onUnauthorized(exchange, "Invalid or expired token");
            }

            // 4. Extract claims and propagate to downstream services
            Claims claims = jwtUtil.extractAllClaims(token);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            log.debug("Authenticated user [{}] with role [{}] accessing: {}", username, role, path);

            // Mutate the request to add user context headers
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-Auth-User", username)
                    .header("X-Auth-Role", role != null ? role : "USER")
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }

    // ------------------------------------------------------------------ //
    //  Helper methods
    // ------------------------------------------------------------------ //

    /**
     * Checks if the given path matches any of the open (public) endpoint
     * prefixes.
     */
    private boolean isOpenEndpoint(String path) {
        Predicate<String> isOpen = prefix -> path.startsWith(prefix);
        return OPEN_ENDPOINTS.stream().anyMatch(isOpen);
    }

    /**
     * Short-circuits the filter chain with a 401 Unauthorized response.
     */
    private Mono<Void> onUnauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("X-Auth-Error", message);
        return response.setComplete();
    }

    // ------------------------------------------------------------------ //
    //  Configuration class (required by AbstractGatewayFilterFactory)
    // ------------------------------------------------------------------ //

    /**
     * Empty configuration POJO. Can be extended in the future to accept
     * per-route parameters (e.g. required roles).
     */
    public static class Config {
        // Intentionally empty - no per-route configuration needed yet.
        // Future enhancement: add a "requiredRole" field for role-based
        // access control at the route level.
    }
}
