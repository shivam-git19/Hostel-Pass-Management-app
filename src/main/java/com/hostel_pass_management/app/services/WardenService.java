package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.model.Warden;
import com.hostel_pass_management.app.repos.WardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WardenService {
    @Autowired
    private WardenRepository wardenRepository;

    public Warden registerWarden(Warden warden) {
        return wardenRepository.save(warden);
    }

    public Warden login(String email, String password) {
        Warden warden = wardenRepository.findByEmail(email);
        if (warden != null && warden.getPassword().equals(password)) {
            return warden;
        }
        return null;
    }

    // Method to update a Warden's information
    public Warden updateWarden(Long id, Warden wardenDetails) {
        Optional<Warden> existingWardenOpt = wardenRepository.findById(id);
        if (existingWardenOpt.isPresent()) {
            Warden existingWarden = existingWardenOpt.get();
            existingWarden.setName(wardenDetails.getName());
            existingWarden.setEmail(wardenDetails.getEmail());
            existingWarden.setContactNumber(wardenDetails.getContactNumber());
            return wardenRepository.save(existingWarden);
        }
        return null; // Return null if the warden does not exist
    }

    // Method to delete a Warden by ID
    public void deleteWarden(Long id) {
        wardenRepository.deleteById(id);
    }
}
