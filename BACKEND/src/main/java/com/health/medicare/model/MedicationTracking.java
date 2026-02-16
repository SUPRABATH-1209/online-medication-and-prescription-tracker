package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class MedicationTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackingId;

    private LocalDate date;
    private String status;     // Taken / Missed
    private String updatedBy;  // Staff / Patient

    @ManyToOne
    @JoinColumn(name = "pm_id")
    private PrescriptionMedicine prescriptionMedicine;
}
