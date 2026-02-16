package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PrescriptionMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pmId;

    private String dosage;
    private String frequency;
    private int durationDays;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
}
