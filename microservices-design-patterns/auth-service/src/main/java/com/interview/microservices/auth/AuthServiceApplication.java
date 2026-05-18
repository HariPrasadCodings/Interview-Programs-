package com.interview.microservices.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Auth Service - provides JWT-based authentication and authorization
 * for the microservices ecosystem.
 *
 * <p>Endpoints exposed:
 * <ul>
 *   <li>POST /auth/register - register a new user</li>
 *   <li>POST /auth/login    - authenticate and receive a JWT</li>
 *   <li>GET  /auth/validate - validate an existing JWT</li>
 * </ul>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
