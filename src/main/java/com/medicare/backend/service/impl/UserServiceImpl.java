package com.medicare.backend.service.impl;

import com.medicare.backend.dto.RegistrationRequestDTO;
import com.medicare.backend.dto.UserResponseDTO;
import com.medicare.backend.dto.LoginRequestDTO; // You will create this in Step 2
import com.medicare.backend.entity.User;
import com.medicare.backend.entity.Doctor;
import com.medicare.backend.entity.Patient;
import com.medicare.backend.repository.UserRepository;
import com.medicare.backend.repository.DoctorRepository;
import com.medicare.backend.repository.PatientRepository;
import com.medicare.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public UserResponseDTO registerUser(RegistrationRequestDTO registrationDto) {
        // 1. Logic: Check if user exists
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        // 2. Map DTO to Base User Entity
        User user = new User();

        // Splitting name: if frontend sends "John Doe", firstName=John, lastName=Doe
        String fullName = registrationDto.getName();
        if (fullName != null && fullName.contains(" ")) {
            user.setFirstName(fullName.substring(0, fullName.indexOf(" ")));
            user.setLastName(fullName.substring(fullName.indexOf(" ") + 1));
        } else {
            user.setFirstName(fullName);
            user.setLastName("");
        }

        user.setEmail(registrationDto.getEmail());
        user.setUsername(registrationDto.getEmail()); // Use email as username for now
        user.setPassword(registrationDto.getPassword()); // Use BCrypt later!
        user.setPhoneNumber(registrationDto.getPhone());
        user.setRole(registrationDto.getRole().toUpperCase());

        User savedUser = userRepository.save(user);

        // 3. Save Role-Specific Data
        if ("DOCTOR".equalsIgnoreCase(registrationDto.getRole())) {
            Doctor doctor = new Doctor();
            doctor.setUser(savedUser);
            doctor.setSpecialization(registrationDto.getSpecialization());
            doctor.setMedicalLicenseNumber(registrationDto.getLicenseNumber());
            // These fields were in your Entity but not mapped before:
            doctor.setHospitalAddress(registrationDto.getClinicAddress());
            doctorRepository.save(doctor);

        } else if ("PATIENT".equalsIgnoreCase(registrationDto.getRole())) {
            Patient patient = new Patient();
            patient.setUser(savedUser);
            patient.setBloodGroup(registrationDto.getBloodGroup());
            patient.setEmergencyContact(registrationDto.getEmergencyContact());
            // Mapping DOB if provided
            patientRepository.save(patient);
        }

        // 4. Return Response DTO
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getFirstName() + " " + savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                null // Token will be added when you implement JWT
        );
    }

    // NEW: Logic for Login
    public UserResponseDTO loginUser(LoginRequestDTO loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with this email"));

        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Invalid password!");
        }

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getRole(),
                "mock-jwt-token" // Placeholder for now
        );
    }
}