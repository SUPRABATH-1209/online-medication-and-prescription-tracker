package com.health.medicare.repository;

import com.health.medicare.model.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine,Long> {
}
