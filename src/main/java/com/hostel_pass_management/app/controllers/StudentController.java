package com.hostel_pass_management.app.controllers;

import com.hostel_pass_management.app.ApiResponse;
import com.hostel_pass_management.app.dto.StudentDTO;
import com.hostel_pass_management.app.model.LoginRequest;
import com.hostel_pass_management.app.model.Student;
import com.hostel_pass_management.app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public Student register(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ApiResponse<StudentDTO>> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // If authentication is successful, retrieve the student details
            String email = loginRequest.getEmail();
            StudentDTO studentDTO = studentService.login(email, loginRequest.getPassword());
            if (studentDTO != null) {
                return ResponseEntity.ok(new ApiResponse<>("Here are the student details", studentDTO));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("Student not found", null));
            }
        } catch (AuthenticationException e) {
            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("Invalid credentials", null));
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
