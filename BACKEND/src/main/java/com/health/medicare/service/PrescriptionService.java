package com.health.medicare.service;

import com.health.medicare.dto.request.PrescriptionRequestDto;
import com.health.medicare.dto.response.PrescriptionResponseDto;

import java.util.List;

public interface PrescriptionService {
    PrescriptionResponseDto createPrescription(PrescriptionRequestDto dto);

    List<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId);

    PrescriptionResponseDto getPrescriptionById(Long id);
}
