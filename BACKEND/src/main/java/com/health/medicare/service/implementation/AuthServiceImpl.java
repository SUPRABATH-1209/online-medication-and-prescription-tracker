package com.health.medicare.service.implementation;

import com.health.medicare.dto.request.LoginRequestDto;
import com.health.medicare.dto.request.RegistrationRequestDto;
import com.health.medicare.dto.response.LoginResponseDto;
import com.health.medicare.exception.InvalidCredentialsException;
import com.health.medicare.model.Doctor;
import com.health.medicare.model.Patient;
import com.health.medicare.model.Staff;
import com.health.medicare.model.Ward;
import com.health.medicare.repository.*;
import com.health.medicare.security.JwtUtil;
import com.health.medicare.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final DoctorRepository doctorRepository;
    private final StaffRepository staffRepository;
    private final PatientRepository patientRepository;
    private final WardRepository wardRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(DoctorRepository doctorRepository,
                           StaffRepository staffRepository,
                           PatientRepository patientRepository,
                           WardRepository wardRepository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.doctorRepository = doctorRepository;
        this.staffRepository = staffRepository;
        this.patientRepository = patientRepository;
        this.wardRepository = wardRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        // Use trim() to ensure no accidental spaces break the login
        String email = dto.getEmail().trim().toLowerCase();
        String rawPassword = dto.getPassword();

        // 1. Try DOCTOR
        var doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent() && passwordEncoder.matches(rawPassword, doctor.get().getPassword())) {
            String token = jwtUtil.generateToken(email, "DOCTOR");
            return new LoginResponseDto(token, "DOCTOR");
        }

        // 2. Try STAFF
        var staff = staffRepository.findByEmail(email);
        if (staff.isPresent() && passwordEncoder.matches(rawPassword, staff.get().getPassword())) {
            String token = jwtUtil.generateToken(email, "STAFF");
            return new LoginResponseDto(token, "STAFF");
        }

        // 3. Try PATIENT
        var patient = patientRepository.findByEmail(email);
        if (patient.isPresent() && passwordEncoder.matches(rawPassword, patient.get().getPassword())) {
            String token = jwtUtil.generateToken(email, "PATIENT");
            return new LoginResponseDto(token, "PATIENT");
        }

        throw new InvalidCredentialsException("Invalid email or password");
    }

    @Override
    @Transactional // Ensures data integrity
    public LoginResponseDto register(RegistrationRequestDto dto) {
        // Standardize Email and Role
        String email = dto.getEmail().trim().toLowerCase();
        String role = dto.getRole().toUpperCase();

        // Global check: Email must be unique across all tables
        if (doctorRepository.findByEmail(email).isPresent() ||
                staffRepository.findByEmail(email).isPresent() ||
                patientRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered: " + email);
        }

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

        switch (role) {
            case "DOCTOR":
                Doctor doctor = new Doctor();
                doctor.setName(dto.getName());
                doctor.setEmail(email);
                doctor.setPassword(encryptedPassword);
                doctor.setPhone(dto.getPhone());
                doctor.setSpecialization(dto.getSpecialization() != null ? dto.getSpecialization() : "General Medicine");
                doctorRepository.save(doctor);
                break;

            case "PATIENT":
                Patient patient = new Patient();
                patient.setName(dto.getName());
                patient.setEmail(email);
                patient.setPassword(encryptedPassword);
                patient.setPhone(dto.getPhone());
                patient.setAge(dto.getAge() != null ? dto.getAge() : 0);
                patient.setGender(dto.getGender() != null ? dto.getGender() : "Other");
                patient.setAddress(dto.getAddress());
                if (dto.getWardId() != null) {
                    wardRepository.findById(dto.getWardId()).ifPresent(patient::setWard);
                }
                patientRepository.save(patient);
                break;

            case "STAFF":
                Staff staff = new Staff();
                staff.setName(dto.getName());
                staff.setEmail(email);
                staff.setPassword(encryptedPassword);
                staff.setPhone(dto.getPhone());
                // Use a default if the DTO role is the generic "STAFF" string
                staff.setRole(dto.getSpecialization() != null ? dto.getSpecialization() : "Medical Staff");
                staff.setShift(dto.getShift() != null ? dto.getShift() : "Morning");
                if (dto.getWardId() != null) {
                    wardRepository.findById(dto.getWardId()).ifPresent(staff::setWard);
                }
                staffRepository.save(staff);
                break;

            default:
                throw new RuntimeException("Invalid role provided: " + role);
        }

        // Return token so user is "Auto-Logged In" after registration
        String token = jwtUtil.generateToken(email, role);
        return new LoginResponseDto(token, role);
    }
}