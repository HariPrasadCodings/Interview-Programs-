package com.interview.microservices.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

/**
 * Utility class for JWT token validation at the API Gateway layer.
 *
 * <h3>Responsibilities</h3>
 * <ul>
 *   <li>Validate the token signature using a shared HMAC-SHA secret.</li>
 *   <li>Check token expiration.</li>
 *   <li>Extract claims (subject, role, etc.) for downstream propagation.</li>
 * </ul>
 *
 * <h3>Important design decisions</h3>
 * <ul>
 *   <li>The gateway does <b>not</b> issue tokens - that is the responsibility
 *       of the auth-service. The gateway only <em>validates</em>.</li>
 *   <li>The signing secret must be identical across the auth-service
 *       (issuer) and the gateway (validator). In production this should be
 *       managed via Spring Cloud Config or a secrets manager.</li>
 *   <li>The secret is injected from the {@code jwt.secret} property
 *       (Base64-encoded) and converted to an HMAC-SHA {@link SecretKey}.</li>
 * </ul>
 *
 * <p><b>Interview note:</b> In microservices, JWT is often used for
 * <em>stateless</em> authentication. The gateway validates the token once,
 * then passes trusted user info via headers - downstream services trust
 * these headers because they only accept traffic from the gateway (network
 * policy / service mesh).</p>
 */
@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private final SecretKey signingKey;

    /**
     * Constructs the utility with a Base64-encoded HMAC secret read from
     * configuration ({@code jwt.secret}).
     *
     * @param secret Base64-encoded secret string
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // ------------------------------------------------------------------ //
    //  Public API
    // ------------------------------------------------------------------ //

    /**
     * Validates the given JWT token. Throws an appropriate exception if the
     * token is invalid, expired, or tampered with.
     *
     * @param token the raw JWT string (without "Bearer " prefix)
     * @throws ExpiredJwtException      if the token has expired
     * @throws MalformedJwtException    if the token structure is invalid
     * @throws SignatureException        if the signature does not match
     * @throws UnsupportedJwtException  if the token uses an unsupported algorithm
     * @throws IllegalArgumentException if the token is null or empty
     */
    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(signingKey)
                    .build()
                    .parseSignedClaims(token);
            log.debug("JWT token validated successfully");
        } catch (ExpiredJwtException e) {
            log.warn("JWT token expired: {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            log.warn("Malformed JWT token: {}", e.getMessage());
            throw e;
        } catch (SignatureException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            log.warn("Unsupported JWT token: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * <p>The caller should invoke {@link #validateToken(String)} first, or
     * be prepared to handle validation exceptions.</p>
     *
     * @param token the raw JWT string
     * @return the {@link Claims} payload
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Convenience method to extract the subject (typically the username)
     * from a JWT token.
     *
     * @param token the raw JWT string
     * @return the subject claim
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Convenience method to check whether the token has expired.
     *
     * @param token the raw JWT string
     * @return {@code true} if the token expiration date is in the past
     */
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new java.util.Date());
    }
}
