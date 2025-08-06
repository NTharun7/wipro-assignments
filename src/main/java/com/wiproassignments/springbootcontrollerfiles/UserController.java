package com.wiproassignments.springbootcontrollerfiles;

import com.wiproassignments.springbootcontrollerfiles.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "Received user: " + user.getFirstName() + " " + user.getLastName() + ", Age: " + user.getAge();
    }
}
