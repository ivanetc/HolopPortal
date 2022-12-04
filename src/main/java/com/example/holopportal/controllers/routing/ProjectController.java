package com.example.holopportal.controllers.routing;


import com.example.holopportal.project.views.NewProjectForm;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;



@Controller
public class ProjectController {

    @Inject
    UserService userService;


    @GetMapping("/info")
    public String information(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("newProjectForm", new NewProjectForm());
        return "info";
    }
}
