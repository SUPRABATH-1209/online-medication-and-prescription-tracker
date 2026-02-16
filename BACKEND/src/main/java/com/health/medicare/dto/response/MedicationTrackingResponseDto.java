package com.health.medicare.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MedicationTrackingResponseDto {

    private String patientName;
    private String medicineName;
    private LocalDate date;
    private String status;

}
