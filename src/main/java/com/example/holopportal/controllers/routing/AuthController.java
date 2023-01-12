package com.example.holopportal.controllers.routing;

import com.example.holopportal.user.services.UserService;
import com.example.holopportal.user.views.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.inject.Inject;

@Controller
public class AuthController {

    @Inject
    UserService userService;

    @GetMapping("/login")
    public String login(Model model, @ModelAttribute UserForm userForm) {
        model.addAttribute("newUserForm", userForm);
        // todo add model and pass empty UserForm into model
        // look at TasksController in routing

        // add into template themeleaf form (look at newTask templeta)

        // maybe you will need to create getter and setters in form
        return "/login";
    }
}
