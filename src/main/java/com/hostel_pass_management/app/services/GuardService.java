package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.model.Guard;
import com.hostel_pass_management.app.repos.GuardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class GuardService {

    @Autowired
    private GuardRepository guardRepository;

    // Method to add a new Guard
    public Guard addGuard(Guard guard) {
        return guardRepository.save(guard);
    }

    // Method to retrieve all Guards
    public List<Guard> getAllGuards() {
        return guardRepository.findAll();
    }

    // Method to find a Guard by ID
    public Guard getGuardById(Long id) {
        return guardRepository.findById(id).orElse(null);
    }

    // Method to update a Guard's information
    public Guard updateGuard(Long id, Guard guardDetails) {
        Guard existingGuard = getGuardById(id);
        if (existingGuard != null) {
            existingGuard.setName(guardDetails.getName());
            existingGuard.setContactNumber(guardDetails.getContactNumber());
            return guardRepository.save(existingGuard);
        }
        return null;
    }

    // Method to delete a Guard by ID
    public void deleteGuard(Long id) {
        guardRepository.deleteById(id);
    }

}
