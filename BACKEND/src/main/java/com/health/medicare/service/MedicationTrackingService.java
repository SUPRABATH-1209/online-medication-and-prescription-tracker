package com.health.medicare.service;

import com.health.medicare.dto.request.MedicationTrackingRequestDto;
import com.health.medicare.dto.response.MedicationTrackingResponseDto;

import java.util.List;

public interface MedicationTrackingService {

    MedicationTrackingResponseDto updateMedicationStatus(
            MedicationTrackingRequestDto dto);

    List<MedicationTrackingResponseDto> getTrackingByPatient(Long patientId);
}
