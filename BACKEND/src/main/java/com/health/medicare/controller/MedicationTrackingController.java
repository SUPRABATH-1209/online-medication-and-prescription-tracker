package com.health.medicare.controller;

import com.health.medicare.dto.request.MedicationTrackingRequestDto;
import com.health.medicare.dto.response.MedicationTrackingResponseDto;
import com.health.medicare.service.MedicationTrackingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
public class MedicationTrackingController {

    private final MedicationTrackingService trackingService;

    public MedicationTrackingController(
            MedicationTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    // MARK MEDICINE TAKEN → STAFF, PATIENT
    @PreAuthorize("hasAnyRole('STAFF','PATIENT')")
    @PostMapping("/mark-taken")
    public MedicationTrackingResponseDto markMedicineTaken(
            @RequestBody MedicationTrackingRequestDto dto) {
        return trackingService.updateMedicationStatus(dto);
    }

    // VIEW SCHEDULE → STAFF, PATIENT
    @PreAuthorize("hasAnyRole('STAFF','PATIENT')")
    @GetMapping("/schedule/{patientId}")
    public List<MedicationTrackingResponseDto> getMedicationSchedule(
            @PathVariable Long patientId) {
        return trackingService.getTrackingByPatient(patientId);
    }
}
