package com.example.holopportal.controllers.routing;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegController {
    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }
}
