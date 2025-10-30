package com.rinit.springbootcrud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "students"; // ✅ Must match templates/index.html
    }
}
