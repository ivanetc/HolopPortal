package com.example.holopportal.controllers.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegController {
    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }
}
