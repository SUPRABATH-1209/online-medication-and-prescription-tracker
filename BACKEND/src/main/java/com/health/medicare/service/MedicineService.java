package com.health.medicare.service;

import com.health.medicare.dto.request.MedicineRequestDto;
import com.health.medicare.dto.response.MedicineResponseDto;

import java.util.List;

public interface MedicineService {
    MedicineResponseDto addMedicine(MedicineRequestDto dto);

    List<MedicineResponseDto> getAllMedicines();

}
