package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String name;
    private String specialization;
    private String phone;
    private String email;
    private String password;
}
