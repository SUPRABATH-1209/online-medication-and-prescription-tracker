package com.health.medicare.controller;

import com.health.medicare.dto.request.PatientRequestDto;
import com.health.medicare.dto.response.PatientResponseDto;
import com.health.medicare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public PatientResponseDto createPatient(
          @Valid @RequestBody PatientRequestDto dto) {
        return patientService.createPatient(dto);
    }

    @GetMapping("/{id}")
    public PatientResponseDto getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping
    public List<PatientResponseDto> getAllPatients() {
        return patientService.getAllPatients();
    }
}
