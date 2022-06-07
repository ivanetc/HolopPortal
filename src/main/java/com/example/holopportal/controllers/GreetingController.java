package com.example.holopportal.controllers;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @Inject
    UserService userService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        return "greeting";
    }

    @GetMapping("/")
    public String greeting(Model model) {
        return "temp";
    }

}