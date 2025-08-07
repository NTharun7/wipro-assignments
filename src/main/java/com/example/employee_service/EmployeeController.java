package com.example.employee_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee/message")
    public String getMessage() {
        return "Employee Service is Up";
    }
}
