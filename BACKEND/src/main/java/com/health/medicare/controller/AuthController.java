package com.health.medicare.controller;

import com.health.medicare.dto.request.LoginRequestDto;
import com.health.medicare.dto.request.RegistrationRequestDto;
import com.health.medicare.dto.response.LoginResponseDto;
import com.health.medicare.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication Controller
 * CHANGE: Added /register endpoint
 * WHY: Frontend needs both login AND registration
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * LOGIN ENDPOINT
     * POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto dto) {

        LoginResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * REGISTRATION ENDPOINT
     * CHANGE: NEW endpoint added
     * POST /api/auth/register
     *
     * Accepts: Doctor, Patient, or Staff registration data
     * Returns: JWT token + role
     */
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDto> register(
            @Valid @RequestBody RegistrationRequestDto dto) {

        LoginResponseDto response = authService.register(dto);
        return ResponseEntity.ok(response);
    }
}