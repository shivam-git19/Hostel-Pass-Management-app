package com.hostel_pass_management.app.controllers;

import com.hostel_pass_management.app.model.Guard;
import com.hostel_pass_management.app.services.GuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guards")
public class GuardController {
    @Autowired
    private GuardService guardService;

    // Endpoint to add a new Guard
    @PostMapping
    public ResponseEntity<Guard> addGuard(@RequestBody Guard guard) {
        Guard savedGuard = guardService.addGuard(guard);
        return ResponseEntity.ok(savedGuard);
    }

    // Endpoint to retrieve all Guards
    @GetMapping
    public ResponseEntity<List<Guard>> getAllGuards() {
        List<Guard> guards = guardService.getAllGuards();
        return ResponseEntity.ok(guards);
    }

    // Endpoint to retrieve a Guard by ID
    @GetMapping("/{id}")
    public ResponseEntity<Guard> getGuardById(@PathVariable Long id) {
        Guard guard = guardService.getGuardById(id);
        return guard != null ? ResponseEntity.ok(guard) : ResponseEntity.notFound().build();
    }

    // Endpoint to update a Guard's information
    @PutMapping("/{id}")
    public ResponseEntity<Guard> updateGuard(@PathVariable Long id, @RequestBody Guard guardDetails) {
        Guard updatedGuard = guardService.updateGuard(id, guardDetails);
        return updatedGuard != null ? ResponseEntity.ok(updatedGuard) : ResponseEntity.notFound().build();
    }

    // Endpoint to delete a Guard by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuard(@PathVariable Long id) {
        guardService.deleteGuard(id);
        return ResponseEntity.noContent().build();
    }
}