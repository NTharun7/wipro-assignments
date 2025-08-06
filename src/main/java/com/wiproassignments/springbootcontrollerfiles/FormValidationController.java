package com.wiproassignments.springbootcontrollerfiles;

import com.wiproassignments.springbootcontrollerfiles.model.Student;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormValidationController {

    // Show the form
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "studentform"; // View name: studentform.html
    }

    // Handle form submission
    @PostMapping("/form")
    public String submitForm(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result) {
        if (result.hasErrors()) {
            return "studentform"; // Stay on form with validation errors
        }
        return "formsuccess"; // Show success view
    }
}
