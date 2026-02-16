package com.health.medicare.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JWT Utility Class - Generates and validates JWT tokens
 * CHANGE: Added this entire class for JWT support
 * WHY: Frontend needs real JWT tokens for authenticated API calls
 */
@Component
public class JwtUtil {

    // Secret key for signing tokens (keep this secure in production!)
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity: 24 hours (in milliseconds)
    private final long EXPIRATION_TIME = 86400000; // 24 hours

    /**
     * Generate JWT token for a user
     * @param email - user's email
     * @param role - user's role (DOCTOR, PATIENT, STAFF)
     * @return JWT token string
     */
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)                           // Store email in token
                .claim("role", role)                         // Store role in token
                .setIssuedAt(new Date())                     // Token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)                        // Sign with secret key
                .compact();
    }

    /**
     * Extract email from JWT token
     */
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validate JWT token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}