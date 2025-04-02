package com.hostel_pass_management.app.controllers;

import com.hostel_pass_management.app.dto.OutPassRequest;
import com.hostel_pass_management.app.services.OutPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outpass")
public class OutPassController {

    private final OutPassService outPassService;

    @Autowired
    public OutPassController(OutPassService outPassService) {
        this.outPassService = outPassService;
    }

    @PostMapping
    public ResponseEntity<String> createOutPass(@Validated @RequestBody OutPassRequest outPassRequest) {
        outPassService.createOutPass(outPassRequest);
        return new ResponseEntity<>("OutPass created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutPassRequest> getOutPass(@PathVariable Integer id) {
        OutPassRequest outPass = outPassService.getOutPassById(id);
        return new ResponseEntity<>(outPass, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOutPass(@PathVariable Integer id, @RequestBody OutPassRequest outPassRequest) {
        outPassService.updateOutPass(id, outPassRequest);
        return new ResponseEntity<>("OutPass updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOutPass(@PathVariable Integer id) {
        outPassService.deleteOutPass(id);
        return new ResponseEntity<>("OutPass deleted successfully", HttpStatus.NO_CONTENT);
    }
}
