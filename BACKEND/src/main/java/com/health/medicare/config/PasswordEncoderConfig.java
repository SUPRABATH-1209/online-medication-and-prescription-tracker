package com.health.medicare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password Encryption Configuration
 * CHANGE: Added BCrypt password encoder
 * WHY: NEVER store passwords in plain text! This encrypts them securely.
 *
 * HOW IT WORKS:
 * - When user registers: password is encrypted before saving to database
 * - When user logs in: entered password is compared with encrypted password
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Create BCrypt password encoder bean
     * BCrypt is industry-standard for password hashing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}