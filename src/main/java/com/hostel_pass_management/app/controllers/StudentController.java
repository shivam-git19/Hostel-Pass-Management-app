package com.hostel_pass_management.app.controllers;

import com.hostel_pass_management.app.ApiResponse;
import com.hostel_pass_management.app.model.LoginRequest;
import com.hostel_pass_management.app.model.Student;
import com.hostel_pass_management.app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public Student register(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Student>> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Student student = studentService.login(email, password);
        if (student != null) {
            return ResponseEntity.ok(new ApiResponse<>("Here are the student details", student));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Student not found", null));
        }
    }

    // Endpoint to update a Student's information
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if student not found
        }
    }

    // Endpoint to delete a Student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}
