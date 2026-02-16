package com.health.medicare.service.implementation;

import com.health.medicare.dto.request.MedicationTrackingRequestDto;
import com.health.medicare.dto.response.MedicationTrackingResponseDto;
import com.health.medicare.model.MedicationTracking;
import com.health.medicare.model.PrescriptionMedicine;
import com.health.medicare.repository.MedicationTrackingRepository;
import com.health.medicare.repository.PrescriptionMedicineRepository;
import com.health.medicare.service.MedicationTrackingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationTrackingServiceImpl implements MedicationTrackingService {

    private final MedicationTrackingRepository trackingRepository;
    private final PrescriptionMedicineRepository prescriptionMedicineRepository;

    public MedicationTrackingServiceImpl(MedicationTrackingRepository trackingRepository,
                                         PrescriptionMedicineRepository prescriptionMedicineRepository) {
        this.trackingRepository = trackingRepository;
        this.prescriptionMedicineRepository = prescriptionMedicineRepository;
    }

    @Override
    public MedicationTrackingResponseDto updateMedicationStatus(
            MedicationTrackingRequestDto dto) {

        PrescriptionMedicine pm = prescriptionMedicineRepository
                .findById(dto.getPrescriptionMedicineId())
                .orElseThrow(() -> new RuntimeException("Prescription medicine not found"));

        MedicationTracking tracking = new MedicationTracking();
        tracking.setPrescriptionMedicine(pm);
        tracking.setDate(dto.getDate());
        tracking.setStatus(dto.getStatus());
        tracking.setUpdatedBy(dto.getUpdatedBy());

        trackingRepository.save(tracking);

        return new MedicationTrackingResponseDto(
                pm.getPrescription().getPatient().getName(),
                pm.getMedicine().getMedicineName(),
                dto.getDate(),
                dto.getStatus()
        );
    }

    @Override
    public List<MedicationTrackingResponseDto> getTrackingByPatient(Long patientId) {

        return trackingRepository.findByPrescriptionMedicinePrescriptionPatientPatientId(patientId)
                .stream()
                .map(t -> new MedicationTrackingResponseDto(
                        t.getPrescriptionMedicine().getPrescription().getPatient().getName(),
                        t.getPrescriptionMedicine().getMedicine().getMedicineName(),
                        t.getDate(),
                        t.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
