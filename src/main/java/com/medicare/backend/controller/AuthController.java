package com.medicare.backend.controller;

import com.medicare.backend.dto.LoginRequestDTO;
import com.medicare.backend.dto.RegistrationRequestDTO;
import com.medicare.backend.dto.UserResponseDTO;
import com.medicare.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3001") // Allow React to talk to Spring
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO request) {
        UserResponseDTO response = userService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request) {
        UserResponseDTO response = userService.loginUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}