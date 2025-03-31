package com.hostel_pass_management.app.repos;

import com.hostel_pass_management.app.model.Warden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardenRepository extends JpaRepository<Warden, Long> {
    Warden findByEmail(String email);
}
