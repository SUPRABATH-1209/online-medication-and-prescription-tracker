package com.medicare.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String specialization;
    private Integer experienceYears;
    private String hospitalName;
    private String hospitalPhone;
    private String medicalLicenseNumber;

    @Column(columnDefinition = "TEXT")
    private String hospitalAddress;
}