package com.interview.microservices.discovery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the Eureka Server.
 *
 * <h3>Why we need this:</h3>
 * <p>Spring Security is on the classpath to protect the Eureka dashboard
 * and REST API with basic auth. However, Eureka clients communicate via
 * REST and do NOT send CSRF tokens, so we must disable CSRF for the
 * {@code /eureka/**} endpoints. Without this, client registrations would
 * fail with 403 Forbidden.</p>
 *
 * <h3>Development vs Production:</h3>
 * <ul>
 *   <li><b>Dev (current):</b> All endpoints are permitted without authentication
 *       for easier local development and testing.</li>
 *   <li><b>Production:</b> You would typically enable HTTP Basic auth for
 *       the dashboard and require clients to supply credentials in their
 *       {@code eureka.client.serviceUrl.defaultZone} URL, e.g.,
 *       {@code http://user:password@localhost:8761/eureka/}</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the Eureka Server.
     *
     * <p>Key decisions:</p>
     * <ul>
     *   <li>CSRF is disabled because Eureka clients use REST calls (not
     *       browser forms) to register, renew leases, and fetch the registry.
     *       CSRF protection would reject these legitimate requests.</li>
     *   <li>All requests are permitted to simplify local development. In
     *       production, restrict access with {@code .httpBasic()} and
     *       role-based authorization.</li>
     * </ul>
     *
     * @param http the {@link HttpSecurity} to configure
     * @return the built {@link SecurityFilterChain}
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF - Eureka clients send REST requests, not browser forms,
            // so they never include a CSRF token. Without this, registration fails.
            .csrf(csrf -> csrf.disable())

            // Allow all requests without authentication for development simplicity.
            // In production, replace with:
            //   .authorizeHttpRequests(auth -> auth
            //       .requestMatchers("/actuator/**").permitAll()
            //       .anyRequest().authenticated()
            //   )
            //   .httpBasic(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
