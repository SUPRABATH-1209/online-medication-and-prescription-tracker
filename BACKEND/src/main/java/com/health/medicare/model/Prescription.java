package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    private LocalDate prescriptionDate;
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionMedicine> prescriptionMedicines;

}
