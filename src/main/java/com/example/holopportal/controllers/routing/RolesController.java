package com.example.holopportal.controllers.routing;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.Role;
import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.services.RoleService;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/roles")
public class RolesController {

    @Inject
    RoleService roleService;

    @Inject
    UserService userService;

    @GetMapping()
    public String getScreenplays(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("roles", roleService.getAllRoles());

        return "roles";
    }

    @GetMapping("/{id}")
    public String getTasks(Model model, @PathVariable int id, @RequestParam(required = false) boolean isNew) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isNew", isNew);

        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
            return "fragments/singleRole";
        } else {
            model.addAttribute("roles", roleService.getAllRoles());
            return "roles";
        }
    }

    private User getCurrentUser() {
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "please login");
        }

        return currentUser.get();
    }
}
