package com.example.holopportal.controllers.routing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        // todo add model and pass empty UserForm into model
        // look at TasksController in routing

        // add into template themeleaf form (look at newTask templeta)

        // maybe you will need to create getter and setters in form
        return "/login";
    }
}
