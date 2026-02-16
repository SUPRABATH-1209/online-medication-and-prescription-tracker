package com.health.medicare.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrescriptionMedicineResponseDto {

    private String medicineName;
    private String dosage;
    private String frequency;
    private int durationDays;
}
