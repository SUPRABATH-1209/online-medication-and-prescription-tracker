package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String name;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String email;
    private String password;


    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;
}
