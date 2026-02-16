package com.health.medicare.model;

import com.health.medicare.model.Ward;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    private String name;
    private String role;   // Nurse / Assistant
    private String shift;  // Morning / Evening / Night
    private String phone;
    private String email;
    private String password;


    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;
}
