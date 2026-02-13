package com.medicare.backend.service.impl;

import com.medicare.backend.dto.RegistrationRequestDTO;
import com.medicare.backend.dto.UserResponseDTO;
import com.medicare.backend.entity.User;
import com.medicare.backend.repository.UserRepository;
import com.medicare.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDTO registerUser(RegistrationRequestDTO registrationDto) {
        // 1. Logic: Check if user exists
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        // 2. Map DTO to Entity
        User user = modelMapper.map(registrationDto, User.class);

        // Note: In a real app, you MUST encrypt the password here
        // user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        // 3. Save to Database
        User savedUser = userRepository.save(user);

        // 4. Map saved Entity back to Response DTO
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }
}