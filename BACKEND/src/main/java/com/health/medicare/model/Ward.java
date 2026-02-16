package com.health.medicare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wardId;

    private String wardName;
    private String location;
}
