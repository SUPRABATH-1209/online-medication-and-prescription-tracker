package com.health.medicare.repository;

import com.health.medicare.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
}
