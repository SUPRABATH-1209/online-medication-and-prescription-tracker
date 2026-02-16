package com.health.medicare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration
 * CHANGE: Temporarily disabled authentication for TESTING
 * WHY: Need to test login/register first, then add JWT filter later
 *
 * NOTE: After testing, we'll add JWT authentication filter here
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                // Disable CSRF for REST APIs
                .csrf(csrf -> csrf.disable())

                // CHANGE: Allow ALL requests for now (TESTING ONLY!)
                // After testing works, we'll add JWT authentication
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Allow all for testing
                )

                // Disable default login forms
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}