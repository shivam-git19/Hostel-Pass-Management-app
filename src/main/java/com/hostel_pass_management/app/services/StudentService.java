package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.dto.StudentDTO;
import com.hostel_pass_management.app.model.Student;
import com.hostel_pass_management.app.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student registerStudent(Student student) {
        // Encode the password before saving
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public StudentDTO login(String email, String password) {
        Student student = studentRepository.findByEmail(email);
        if (student != null && passwordEncoder.matches(password, student.getPassword())) {
            return new StudentDTO(student.getName(), student.getRegNum(), student.getCourse(), student.getContactNumber(), student.getGuardianContact(), student.getEmail(), student.getRoom()); // Return the student if the password matches
        }
        return null; // Return null if the student does not exist or password does not match
    }

    // Method to update a Student's information
    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> existingStudentOpt = studentRepository.findById(id);
        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setContactNumber(studentDetails.getContactNumber());
            return studentRepository.save(existingStudent);
        }
        return null; // Return null if the student does not exist
    }

    // Method to delete a Student by ID
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}