package com.example.holopportal.controllers.routing;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.services.ScreenplayService;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/screenplay")
public class ScreenPlayController {

    @Inject
    UserService userService;
    @Inject
    ScreenplayService screenPlayService;

    @GetMapping()
    public String getTasks(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("screenplays", screenPlayService.getAllScreenplays());

        return "screenplays";
    }

    private User getCurrentUser() {
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "please login");
        }

        return currentUser.get();
    }
}
