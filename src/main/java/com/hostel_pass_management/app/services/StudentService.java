package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.model.Student;
import com.hostel_pass_management.app.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student login(String email, String password) {
        Student student = studentRepository.findByEmail(email);
        if (student != null && student.getPassword().equals(password)) {
            return student;
        }
        return null;
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
