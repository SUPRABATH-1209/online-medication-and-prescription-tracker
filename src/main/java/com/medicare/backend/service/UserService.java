package com.medicare.backend.service;

import com.medicare.backend.dto.RegistrationRequestDTO;
import com.medicare.backend.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(RegistrationRequestDTO registrationDto);
}