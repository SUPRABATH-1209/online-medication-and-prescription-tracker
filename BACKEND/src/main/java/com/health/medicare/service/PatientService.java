package com.health.medicare.service;

import com.health.medicare.dto.request.PatientRequestDto;
import com.health.medicare.dto.response.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto dto);

    PatientResponseDto getPatientById(Long patientId);

    List<PatientResponseDto> getAllPatients();
}
