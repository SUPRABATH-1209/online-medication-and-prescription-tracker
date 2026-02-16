package com.health.medicare.service.implementation;

import com.health.medicare.dto.request.PatientRequestDto;
import com.health.medicare.dto.response.PatientResponseDto;
import com.health.medicare.model.Patient;
import com.health.medicare.model.Ward;
import com.health.medicare.repository.PatientRepository;
import com.health.medicare.repository.WardRepository;
import com.health.medicare.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final WardRepository wardRepository;

    // Constructor Injection (industry standard)
    public PatientServiceImpl(PatientRepository patientRepository,
                              WardRepository wardRepository) {
        this.patientRepository = patientRepository;
        this.wardRepository = wardRepository;
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto dto) {

        // 1️⃣ Fetch Ward (validation)
        Ward ward = wardRepository.findById(dto.getWardId())
                .orElseThrow(() -> new RuntimeException("Ward not found"));

        // 2️⃣ Map DTO → Entity
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());
        patient.setAddress(dto.getAddress());
        patient.setWard(ward);

        // 3️⃣ Save Entity
        Patient savedPatient = patientRepository.save(patient);

        // 4️⃣ Map Entity → Response DTO
        return new PatientResponseDto(
                savedPatient.getPatientId(),
                savedPatient.getName(),
                savedPatient.getAge(),
                savedPatient.getGender(),
                savedPatient.getPhone(),
                ward.getWardName()
        );
    }

    @Override
    public PatientResponseDto getPatientById(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return new PatientResponseDto(
                patient.getPatientId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getPhone(),
                patient.getWard().getWardName()
        );
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(patient -> new PatientResponseDto(
                        patient.getPatientId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getGender(),
                        patient.getPhone(),
                        patient.getWard().getWardName()
                ))
                .collect(Collectors.toList());
    }
}
