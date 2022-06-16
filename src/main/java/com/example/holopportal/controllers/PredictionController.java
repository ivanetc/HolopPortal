package com.example.holopportal.controllers;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.prediction.PredictionService;
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

    @Inject
    PredictionService predictionService;

    @GetMapping("/prediction")
    public String predict(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("predictionInfo", predictionService.getCurrentPrediction());
        return "prediction";
    }
}

