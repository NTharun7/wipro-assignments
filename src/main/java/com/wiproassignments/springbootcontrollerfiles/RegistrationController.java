package com.wiproassignments.springbootcontrollerfiles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processForm(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String birthday,
            @RequestParam String profession,
            Model model) {
        
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("birthday", birthday);
        model.addAttribute("profession", profession);

        return "success";
    }
}
