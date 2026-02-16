package com.health.medicare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configuration - CORS Setup
 * CHANGE: Updated port from 5173 to 3000
 * WHY: React (Create React App) runs on port 3000, not 5173 (Vite)
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3001")  // CHANGED: from 5173 to 3000
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}