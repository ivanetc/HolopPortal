package com.example.holopportal.controllers.rest;


import java.util.Optional;

import javax.annotation.Resource;
import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.views.ScreenplayForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import com.example.holopportal.user.views.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/user")
public class RegController {

    @Inject
    UserService userService;

    @PostMapping()
    public RedirectView createTask(Model model, @ModelAttribute UserForm userForm) {
        Optional<User> userOptional = userService.createNewUser(userForm);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/login");


        return redirectView;
    }
}
