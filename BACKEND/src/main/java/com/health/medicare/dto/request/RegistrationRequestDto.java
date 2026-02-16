package com.health.medicare.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for user registration (Doctor, Patient, Staff)
 * CHANGE: Added this file to support registration from frontend
 * WHY: Frontend sends registration data, backend needs to accept it
 */
@Data
public class RegistrationRequestDto {

    // Basic fields (required for all users)
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Role is required")
    private String role;  // DOCTOR, PATIENT, STAFF

    private String phone;

    // Doctor-specific fields
    private String specialization;
    private String licenseNumber;
    private Integer experienceYears;
    private String hospitalName;
    private String hospitalPhone;
    private String hospitalAddress;

    // Patient-specific fields
    private String bloodGroup;
    private String emergencyContact;
    private Integer age;
    private String gender;
    private String address;
    private Long wardId;

    // Staff-specific fields
    private String shift;  // Morning/Evening/Night
}