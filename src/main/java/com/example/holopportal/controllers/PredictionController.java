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

public class PredictionController {

    @Inject
    UserService userService;

    @GetMapping("/prediction")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        return "prediction";
    }
}

class PredictionView {
    public int love = 4;
    public int kindness = 2;
    public int  honesty = 1;
}

