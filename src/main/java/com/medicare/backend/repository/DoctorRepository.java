package com.medicare.backend.repository;

import com.medicare.backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // You can find doctors by specialization for a search feature
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);

    // Find doctor by the hospital name
    List<Doctor> findByHospitalName(String hospitalName);
}