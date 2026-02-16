package com.health.medicare.service.implementation;

import com.health.medicare.dto.request.MedicineRequestDto;
import com.health.medicare.dto.response.MedicineResponseDto;
import com.health.medicare.model.Medicine;
import com.health.medicare.repository.MedicineRepository;
import com.health.medicare.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public MedicineResponseDto addMedicine(MedicineRequestDto dto) {

        Medicine medicine = new Medicine();
        medicine.setMedicineName(dto.getMedicineName());
        medicine.setManufacturer(dto.getManufacturer());

        Medicine saved = medicineRepository.save(medicine);

        return new MedicineResponseDto(
                saved.getMedicineId(),
                saved.getMedicineName(),
                saved.getManufacturer()
        );
    }

    @Override
    public List<MedicineResponseDto> getAllMedicines() {
        return medicineRepository.findAll()
                .stream()
                .map(m -> new MedicineResponseDto(
                        m.getMedicineId(),
                        m.getMedicineName(),
                        m.getManufacturer()
                ))
                .collect(Collectors.toList());
    }
}
