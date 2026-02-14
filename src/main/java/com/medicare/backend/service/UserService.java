package com.medicare.backend.service;

import com.medicare.backend.dto.RegistrationRequestDTO;
import com.medicare.backend.dto.UserResponseDTO;
import com.medicare.backend.dto.LoginRequestDTO;

public interface UserService {
    UserResponseDTO registerUser(RegistrationRequestDTO registrationDto);
    UserResponseDTO loginUser(LoginRequestDTO loginDto); // Add this
}