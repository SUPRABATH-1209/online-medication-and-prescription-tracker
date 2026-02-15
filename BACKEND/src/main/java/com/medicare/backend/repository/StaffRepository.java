package com.medicare.backend.repository;

import com.medicare.backend.entity.Staff;
import com.medicare.backend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    // Find all caregivers/staff assigned to a specific patient
    List<Staff> findByAssignedPatient(Patient patient);

    // Find staff by their job title (e.g., "Nurse" or "Family Member")
    List<Staff> findByJobTitle(String jobTitle);
}