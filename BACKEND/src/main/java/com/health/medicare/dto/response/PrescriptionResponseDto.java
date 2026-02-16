package com.health.medicare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class PrescriptionResponseDto {

    private Long prescriptionId;
    private LocalDate prescriptionDate;
    private String doctorName;
    private String patientName;

    private List<PrescriptionMedicineResponseDto> medicines;
}

