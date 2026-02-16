package com.health.medicare.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PatientRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 0, message = "Age cannot be negative")
    private int age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number"
    )
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Ward ID is required")
    private Long wardId;


}
