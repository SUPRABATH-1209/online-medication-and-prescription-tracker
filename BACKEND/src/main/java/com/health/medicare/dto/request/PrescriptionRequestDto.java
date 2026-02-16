package com.health.medicare.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class PrescriptionRequestDto {

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    private String remarks;

    @NotEmpty(message = "At least one medicine is required")
    private List<PrescriptionMedicineRequestDto> medicines;

    // getters & setters
}
