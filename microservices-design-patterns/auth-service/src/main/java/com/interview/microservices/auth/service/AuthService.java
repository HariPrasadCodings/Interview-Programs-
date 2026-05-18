package com.interview.microservices.auth.service;

import com.interview.microservices.auth.dto.AuthRequest;
import com.interview.microservices.auth.dto.AuthResponse;
import com.interview.microservices.auth.dto.RegisterRequest;
import com.interview.microservices.auth.entity.User;
import com.interview.microservices.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Core authentication service that orchestrates user registration,
 * login (JWT issuance), and token validation.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Register a new user.
     *
     * @param request the registration payload
     * @return an {@link AuthResponse} containing the JWT for the
     *         newly created user
     * @throws IllegalArgumentException if the username or email is
     *                                  already taken
     */
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of("ROLE_USER"))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }

    /**
     * Authenticate a user and issue a JWT.
     *
     * @param request the login payload (username + password)
     * @return an {@link AuthResponse} containing the JWT
     */
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }

    /**
     * Validate an existing JWT (signature + expiry check only).
     *
     * @param token the JWT to validate
     * @return true if the token is valid
     */
    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
