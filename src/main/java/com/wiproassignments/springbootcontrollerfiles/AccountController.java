package com.wiproassignments.springbootcontrollerfiles;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AccountController {

    @GetMapping("/users/{id}/accounts")
    public Map<String, String> getAccounts(
            @PathVariable String id,
            @RequestParam String type,
            @RequestParam String status) {

        // Dummy response
        Map<String, String> response = new HashMap<>();
        response.put("User ID", id);
        response.put("Account Type", type);
        response.put("Account Status", status);
        return response;
    }
}
