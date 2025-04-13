package com.hostel_pass_management.app.dto;

import lombok.Getter;

@Getter
public class StudentDTO {
    private String name;
    private String regNum;
    private String course;
    private Long contactNumber;
    private Long guardianContact;
    private String email;
    private String room;

    public StudentDTO(String name, String regNum, String course, Long contactNumber, Long guardianContact, String email, String room) {
        this.name = name;
        this.regNum = regNum;
        this.course = course;
        this.contactNumber = contactNumber;
        this.guardianContact = guardianContact;
        this.email = email;
        this.room = room;
    }
}
