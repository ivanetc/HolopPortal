package com.example.holopportal.controllers.rest;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.services.ScreenplayService;
import com.example.holopportal.screenplay.views.ScreenplayForm;
import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/screenplay")

public class ScreenplayRestController {

    @Inject
    ScreenplayService screenplayService;

    @Inject
    UserService userService;

    @PostMapping()
    public RedirectView createTask(Model model, @ModelAttribute ScreenplayForm screenplayForm) {
        User currentUser = getCurrentUser();
        screenplayService.createNewScreenplay(screenplayForm, currentUser);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/screenplay");
        return redirectView;
    }

    private User getCurrentUser() {
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "please login");
        }

        return currentUser.get();
    }
}
