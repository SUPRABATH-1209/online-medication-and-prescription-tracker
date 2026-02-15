package com.medicare.backend.repository;

import com.medicare.backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Standard CRUD operations are inherited from JpaRepository
}