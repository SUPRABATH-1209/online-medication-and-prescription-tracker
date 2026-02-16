package com.health.medicare.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PrescriptionMedicineRequestDto {

    @NotNull(message = "Medicine ID is required")
    private Long medicineId;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    @NotBlank(message = "Frequency is required")
    private String frequency;

    @Min(value = 1, message = "Duration must be at least 1 day")
    private int durationDays;

    // getters & setters
}
