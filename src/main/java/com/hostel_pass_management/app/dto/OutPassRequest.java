package com.hostel_pass_management.app.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OutPassRequest {
    @NotNull(message = "ID is required")
    private Integer id;

    @NotBlank(message = "Student Name is required")
    private String studentName;

    @NotNull(message = "Date of OutPass is required")
    private LocalDate date;

    @NotBlank(message = "Reason for OutPass is required")
    private String reason;
}
