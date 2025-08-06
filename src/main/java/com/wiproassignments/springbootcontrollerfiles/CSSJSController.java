package com.wiproassignments.springbootcontrollerfiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CSSJSController {

    @GetMapping("/sample")
    public String samplePage() {
        return "sample";  // sample.html in templates
    }
}
