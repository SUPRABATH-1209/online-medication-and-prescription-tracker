package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;

    private String medicineName;
    private String manufacturer;
}
