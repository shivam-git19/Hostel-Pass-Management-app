package com.hostel_pass_management.app.services;

import com.hostel_pass_management.app.dto.OutPassRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OutPassService {

    private final Map<Integer, OutPassRequest> outPassDatabase = new HashMap<>();

    public void createOutPass(OutPassRequest outPassRequest) {
        outPassDatabase.put(outPassRequest.getId(), outPassRequest);
    }

    public OutPassRequest getOutPassById(Integer id) {
        return outPassDatabase.get(id);
    }

    public void updateOutPass(Integer id, OutPassRequest outPassRequest) {
        outPassDatabase.put(id, outPassRequest);
    }

    public void deleteOutPass(Integer id) {
        outPassDatabase.remove(id);
    }
}
