package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.model.Warden;
import com.hostel_pass_management.app.repos.WardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
