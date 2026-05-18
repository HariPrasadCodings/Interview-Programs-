package com.interview.microservices.auth.controller;

import com.interview.microservices.auth.dto.AuthRequest;
import com.interview.microservices.auth.dto.AuthResponse;
import com.interview.microservices.auth.dto.RegisterRequest;
import com.interview.microservices.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing authentication endpoints.
 *
 * <ul>
 *   <li>POST /auth/register - register a new user and receive a JWT</li>
 *   <li>POST /auth/login    - authenticate and receive a JWT</li>
 *   <li>GET  /auth/validate - validate an existing JWT</li>
 * </ul>
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Register a new user.
     *
     * @param request the registration payload
     * @return 201 Created with the JWT in the response body
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Authenticate an existing user.
     *
     * @param request the login payload (username + password)
     * @return 200 OK with the JWT in the response body
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Validate a JWT.
     *
     * @param token the JWT to validate (passed as a query parameter)
     * @return 200 OK if valid; 401 Unauthorized otherwise
     */
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        boolean isValid = authService.validateToken(token);
        if (isValid) {
            return ResponseEntity.ok("Token is valid");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
    }
}
