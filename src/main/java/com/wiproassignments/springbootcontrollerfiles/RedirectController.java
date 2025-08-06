package com.wiproassignments.springbootcontrollerfiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/example/test")
    public String redirectToAnotherPage() {
        return "redirect:/example/test2"; // 302 redirection
    }

    @GetMapping("/example/test2")
    public String finalPage() {
        return "test2"; // This will render test2.html
    }
}
