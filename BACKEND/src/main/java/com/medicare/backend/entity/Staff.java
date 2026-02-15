package com.medicare.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "staff")
@Data
public class Staff {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String jobTitle; // e.g., "Family Member", "Private Nurse", "Hospital Staff"

    // This links the Staff member to a specific Patient they are caring for
    @ManyToOne
    @JoinColumn(name = "assigned_patient_id")
    private Patient assignedPatient;
}