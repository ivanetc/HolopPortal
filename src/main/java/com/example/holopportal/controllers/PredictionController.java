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
        model.addAttribute("predictionInfo", new PredictionView(2, 4,
                1, 10, 10, 10));
        return "prediction";
    }
}

class PredictionView {
    private final int love_current;
    private final int kindness_current;
    private final int  honesty_current;
    private final int love_target;
    private final int kindness_target;
    private final int  honesty_target;

    public PredictionView(int love_current, int kindness_current, int honesty_current, int love_target,
                          int kindness_target, int honesty_target) {
        this.love_current = love_current;
        this.kindness_current = kindness_current;
        this.honesty_current = honesty_current;
        this.love_target = love_target;
        this.kindness_target = kindness_target;
        this.honesty_target = honesty_target;
    }

    public int getLove_current() {
        return love_current;
    }

    public int getKindness_current() {
        return kindness_current;
    }

    public int getHonesty_current() {
        return honesty_current;
    }

    public int getLove_target() {
        return love_target;
    }

    public int getKindness_target() {
        return kindness_target;
    }

    public int getHonesty_target() {
        return honesty_target;
    }
}

