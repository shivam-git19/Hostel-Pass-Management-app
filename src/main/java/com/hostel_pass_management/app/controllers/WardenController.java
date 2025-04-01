package com.hostel_pass_management.app.controllers;

import com.hostel_pass_management.app.ApiResponse;
import com.hostel_pass_management.app.model.LoginRequest;
import com.hostel_pass_management.app.model.Student;
import com.hostel_pass_management.app.model.Warden;
import com.hostel_pass_management.app.services.WardenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wardens")
public class WardenController {
    @Autowired
    private WardenService wardenService;

    // Endpoint to register a new Warden
    @PostMapping("/register")
    public ResponseEntity<Warden> register(@RequestBody Warden warden) {
        Warden savedWarden = wardenService.registerWarden(warden);
        return ResponseEntity.ok(savedWarden);
    }

    // Endpoint to log in a Warden
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Warden>> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Warden warden = wardenService.login(email, password);
        if (warden != null) {
            return ResponseEntity.ok(new ApiResponse<>("Here are the warden details", warden));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Not found", null));
        }
    }
}
