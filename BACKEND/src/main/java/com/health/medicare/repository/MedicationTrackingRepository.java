package com.health.medicare.repository;

import com.health.medicare.model.MedicationTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationTrackingRepository
        extends JpaRepository<MedicationTracking, Long> {

    List<MedicationTracking>
    findByPrescriptionMedicinePrescriptionPatientPatientId(Long patientId);
}
