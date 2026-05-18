package com.interview.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Spring Cloud Config Server Application.
 *
 * <p>This service acts as the centralized configuration server for all
 * microservices in the e-commerce platform. It implements the
 * <b>Externalized Configuration</b> pattern, one of the key microservices
 * design patterns.</p>
 *
 * <h3>How it works:</h3>
 * <ul>
 *   <li>Config Server starts on port 8888 (the conventional default).</li>
 *   <li>Uses the "native" profile to serve configs from the classpath
 *       ({@code config-repo/} directory) instead of a remote Git repo.</li>
 *   <li>Each microservice (order-service, product-service, etc.) fetches
 *       its configuration from this server at startup via
 *       {@code spring.config.import=configserver:http://localhost:8888}.</li>
 *   <li>Shared configuration is placed in {@code application.yml} inside
 *       config-repo, while service-specific overrides go into
 *       {@code <service-name>.yml} files.</li>
 * </ul>
 *
 * <h3>Interview Key Points:</h3>
 * <ul>
 *   <li><b>Why centralize config?</b> - Avoids duplicating configuration
 *       across multiple services; single source of truth.</li>
 *   <li><b>Config resolution order:</b> service-specific &gt; shared
 *       (application.yml) &gt; local defaults.</li>
 *   <li><b>Refresh scope:</b> Properties annotated with {@code @RefreshScope}
 *       can be updated at runtime via {@code /actuator/refresh} without
 *       restarting the service.</li>
 *   <li><b>Native vs Git backend:</b> Native is classpath/file-based
 *       (good for development); Git backend is used in production for
 *       version control and audit trails.</li>
 * </ul>
 *
 * @see org.springframework.cloud.config.server.EnableConfigServer
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
