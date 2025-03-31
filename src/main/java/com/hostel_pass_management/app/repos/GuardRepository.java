package com.hostel_pass_management.app.repos;

import com.hostel_pass_management.app.model.Guard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardRepository extends JpaRepository<Guard, Long> {
    Guard findByGuardId(String guardId);
}
