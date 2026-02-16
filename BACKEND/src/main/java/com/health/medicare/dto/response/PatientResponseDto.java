package com.health.medicare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientResponseDto {

    private Long patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String wardName;
}
