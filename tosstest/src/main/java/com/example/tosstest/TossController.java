package com.example.tosstest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TossController {
    
    @GetMapping("/")
    public String view() {
        return "index";
    }
    
}
