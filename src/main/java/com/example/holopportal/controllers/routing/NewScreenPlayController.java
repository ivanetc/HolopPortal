package com.example.holopportal.controllers.routing;

import com.example.holopportal.prediction.PredictionService;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class NewScreenPlayController {

    @Inject
    UserService userService;

    @GetMapping("/newscreenplay")
    public String greeting(@RequestParam(required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        return "newscreenplay";
    }
}
