package com.health.medicare.service;

import com.health.medicare.dto.request.LoginRequestDto;
import com.health.medicare.dto.request.RegistrationRequestDto;
import com.health.medicare.dto.response.LoginResponseDto;

/**
 * Authentication Service Interface
 * CHANGE: Added register() method
 * WHY: Frontend needs registration functionality
 */
public interface AuthService {

    LoginResponseDto login(LoginRequestDto dto);

    // NEW METHOD: User registration
    LoginResponseDto register(RegistrationRequestDto dto);
}