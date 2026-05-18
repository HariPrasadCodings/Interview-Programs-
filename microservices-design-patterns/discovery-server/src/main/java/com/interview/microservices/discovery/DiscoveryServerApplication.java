package com.interview.microservices.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Netflix Eureka Server - Service Registry (Pattern #2).
 *
 * <p>This application acts as the central service registry for the entire
 * microservices ecosystem. Every microservice registers itself here on
 * startup and periodically sends heartbeats to indicate it is still alive.</p>
 *
 * <h3>How it works:</h3>
 * <ol>
 *   <li>Each microservice (Eureka Client) sends a POST to /eureka/apps/{appName}
 *       with its host, port, and health-check URL.</li>
 *   <li>The Eureka Server stores this in an in-memory registry and replicates
 *       it to peers (if running in a cluster).</li>
 *   <li>Clients fetch the registry periodically (every 30s by default) so they
 *       can resolve service names to actual host:port pairs.</li>
 *   <li>If a client stops sending heartbeats, the server evicts it after 90s
 *       (configurable via lease settings).</li>
 * </ol>
 *
 * <h3>Interview talking points:</h3>
 * <ul>
 *   <li>Eureka uses client-side discovery: the client pulls the registry and
 *       does load balancing locally (via Spring Cloud LoadBalancer).</li>
 *   <li>Self-preservation mode: if > 85% of instances miss heartbeats, Eureka
 *       assumes a network partition and stops evicting (prevents cascading failures).</li>
 *   <li>We set {@code register-with-eureka=false} and {@code fetch-registry=false}
 *       because this IS the registry; it has no need to register with itself.</li>
 * </ul>
 *
 * @see <a href="http://localhost:8761">Eureka Dashboard</a>
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}
