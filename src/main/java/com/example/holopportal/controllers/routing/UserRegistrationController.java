package com.example.holopportal.controllers.routing;


import com.example.holopportal.user.views.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRegistrationController {
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "registration";
    }
}
