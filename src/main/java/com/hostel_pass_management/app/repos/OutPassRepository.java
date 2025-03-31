package com.hostel_pass_management.app.repos;

import com.hostel_pass_management.app.model.OutPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutPassRepository extends JpaRepository<OutPass, Long> {
    OutPass findBySapId(String sapId);
}
