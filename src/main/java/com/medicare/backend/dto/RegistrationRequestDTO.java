package com.medicare.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegistrationRequestDTO {

    // Common Fields
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Role is required")
    private String role; // "DOCTOR", "PATIENT", or "STAFF"

    private String phone;

    // Doctor Specific Fields
    private String specialization;
    private String licenseNumber;
    private String clinicAddress;

    // Patient Specific Fields
    private String bloodGroup;
    private String emergencyContact;
    private String dateOfBirth;

    // Staff Specific Fields (Caregivers)
    private String department;
    private Long supervisorId; // Links staff to a specific Doctor
}