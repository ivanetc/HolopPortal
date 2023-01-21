package com.example.holopportal.controllers.rest;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import com.example.holopportal.user.views.UserForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/user")
public class RegRestController {

    @Inject
    UserService userService;

    @PostMapping()
    public RedirectView createNewUser(Model model, @ModelAttribute UserForm userForm) {
        Optional<User> userOptional = userService.createNewUser(userForm);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/login");


        return redirectView;
    }
}
