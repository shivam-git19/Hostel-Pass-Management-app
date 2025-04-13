package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.model.Student;
import com.hostel_pass_management.app.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            throw new UsernameNotFoundException("Student not found: " + email);
        }
        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), new ArrayList<>());
    }
}
