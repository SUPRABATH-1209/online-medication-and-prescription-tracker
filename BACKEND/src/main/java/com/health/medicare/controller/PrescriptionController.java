package com.health.medicare.controller;

import com.health.medicare.dto.request.PrescriptionRequestDto;
import com.health.medicare.dto.response.PrescriptionResponseDto;
import com.health.medicare.service.PrescriptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // CREATE PRESCRIPTION → DOCTOR
    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public PrescriptionResponseDto createPrescription(
            @RequestBody PrescriptionRequestDto dto) {
        return prescriptionService.createPrescription(dto);
    }

    // VIEW PRESCRIPTIONS → DOCTOR, STAFF, PATIENT
    @PreAuthorize("hasAnyRole('DOCTOR','STAFF','PATIENT')")
    @GetMapping("/patient/{patientId}")
    public List<PrescriptionResponseDto> getPrescriptionsByPatient(
            @PathVariable Long patientId) {
        return prescriptionService.getPrescriptionsByPatient(patientId);
    }
}
