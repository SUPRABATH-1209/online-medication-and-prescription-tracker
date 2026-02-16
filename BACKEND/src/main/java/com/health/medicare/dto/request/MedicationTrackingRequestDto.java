package com.health.medicare.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
@Data
public class MedicationTrackingRequestDto {

    @NotNull(message = "Prescription medicine ID is required")
    private Long prescriptionMedicineId;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "UpdatedBy is required")
    private String updatedBy;

    // getters & setters
}
