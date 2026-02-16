package com.health.medicare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedicineResponseDto {

    private Long medicineId;
    private String medicineName;
    private String manufacturer;
}
