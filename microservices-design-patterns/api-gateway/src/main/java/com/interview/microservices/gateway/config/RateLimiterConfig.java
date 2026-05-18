package com.interview.microservices.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * Configuration for the Redis-backed
 * {@link org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter}
 * used by the built-in {@code RequestRateLimiter} GatewayFilter.
 *
 * <h3>How rate limiting works in Spring Cloud Gateway</h3>
 * <ol>
 *   <li>A {@link KeyResolver} determines the <em>bucket key</em> for each
 *       request (e.g. by IP address, by authenticated user, etc.).</li>
 *   <li>The {@code RedisRateLimiter} uses a Lua script executed atomically
 *       in Redis to implement a <b>token-bucket</b> algorithm.</li>
 *   <li>Two parameters control the behaviour per route:
 *       <ul>
 *         <li>{@code replenishRate} - tokens added per second</li>
 *         <li>{@code burstCapacity} - max tokens in the bucket</li>
 *       </ul>
 *   </li>
 * </ol>
 *
 * <h3>Key resolver strategies defined here</h3>
 * <ul>
 *   <li><b>ipKeyResolver</b> (primary) - rate-limits by client IP address.
 *       Suitable for public-facing APIs.</li>
 *   <li><b>userKeyResolver</b> - rate-limits by the {@code X-Auth-User}
 *       header set by {@link com.interview.microservices.gateway.filter.AuthenticationFilter}.
 *       Suitable for authenticated APIs where per-user fairness is needed.</li>
 *   <li><b>pathKeyResolver</b> - rate-limits by request path, useful for
 *       protecting specific endpoints from abuse.</li>
 * </ul>
 *
 * <p>To apply rate limiting on a route, add the {@code RequestRateLimiter}
 * filter in {@code application.yml}:</p>
 * <pre>
 * filters:
 *   - name: RequestRateLimiter
 *     args:
 *       redis-rate-limiter.replenishRate: 10
 *       redis-rate-limiter.burstCapacity: 20
 *       key-resolver: "#{@ipKeyResolver}"
 * </pre>
 */
@Configuration
public class RateLimiterConfig {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterConfig.class);

    /**
     * Resolves the rate-limit key based on the client's IP address.
     *
     * <p>This is the <b>primary</b> key resolver and will be injected by
     * default when no explicit {@code key-resolver} SpEL reference is
     * specified in the route filter configuration.</p>
     *
     * @return a {@link KeyResolver} that uses the remote host address
     */
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            String ip = exchange.getRequest().getRemoteAddress() != null
                    ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                    : "unknown";
            log.debug("Rate-limit key (IP): {}", ip);
            return Mono.just(ip);
        };
    }

    /**
     * Resolves the rate-limit key based on the authenticated user's name,
     * extracted from the {@code X-Auth-User} header that is set by
     * {@link com.interview.microservices.gateway.filter.AuthenticationFilter}.
     *
     * <p>Falls back to {@code "anonymous"} if the header is absent (e.g.
     * on open endpoints).</p>
     *
     * @return a {@link KeyResolver} that uses the authenticated username
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            String user = exchange.getRequest().getHeaders().getFirst("X-Auth-User");
            String key = user != null ? user : "anonymous";
            log.debug("Rate-limit key (user): {}", key);
            return Mono.just(key);
        };
    }

    /**
     * Resolves the rate-limit key based on the request path, allowing
     * per-endpoint rate limiting.
     *
     * @return a {@link KeyResolver} that uses the request path
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> {
            String path = exchange.getRequest().getURI().getPath();
            log.debug("Rate-limit key (path): {}", path);
            return Mono.just(path);
        };
    }
}
