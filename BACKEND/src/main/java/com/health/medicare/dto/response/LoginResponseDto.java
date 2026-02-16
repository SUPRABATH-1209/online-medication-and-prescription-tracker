package com.health.medicare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Login Response DTO
 * Structure matches frontend expectations:
 * { "token": "eyJhbGci...", "role": "DOCTOR" }
 */
@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;  // JWT token
    private String role;   // DOCTOR, PATIENT, STAFF
}