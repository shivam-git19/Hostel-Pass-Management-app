package com.hostel_pass_management.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sapId;
    private String name;
    private String course;
    private Long contact;
    private Long guardianContact;
    private String room;
    private String leaveDate;
    private String returnDate;
    private String permission; // 'G' for granted, 'D' for denied
    private String leaveAddress;

}
