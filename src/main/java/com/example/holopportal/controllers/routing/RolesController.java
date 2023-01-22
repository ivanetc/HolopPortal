package com.example.holopportal.controllers.routing;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.Role;
import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.entities.ScreenplayStatus;
import com.example.holopportal.screenplay.services.RoleService;
import com.example.holopportal.screenplay.views.RoleAssignmentForm;
import com.example.holopportal.screenplay.views.RoleForm;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.jetbrains.annotations.NotNull;
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

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("roleForm", new RoleForm());
        return "editRole";
    }

    @GetMapping("/{id}/edit")
    public String newTask(Model model, @PathVariable int id) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());

        Optional<Role> optionalRole = roleService.getRoleById(id);
        if (!optionalRole.isPresent()) {
            model.addAttribute("roleForm", new RoleForm());
        } else {
            Role role = optionalRole.get();
            model.addAttribute("roleForm", mapToRoleForm(role));
        }
        return "editRole";
    }

    @GetMapping("/{id}/assign")
    public String assign(Model model, @PathVariable int id) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("all_workers", userService.getAllWorkersWithoutScreenplayRole());

        Optional<Role> optionalRole = roleService.getRoleById(id);

        if (!optionalRole.isPresent()) {
            return "roles";
        }
        Role role = optionalRole.get();

        RoleAssignmentForm form = new RoleAssignmentForm();
        form.setRoleId(role.id);
        form.setRoleName(role.name);

        model.addAttribute("roleAssignmentForm", form);

        return "roleAssignment";
    }

    @NotNull
    private static RoleForm mapToRoleForm(Role role) {
        RoleForm roleForm = new RoleForm();
        roleForm.setId(role.id);
        roleForm.setName(role.name);
        roleForm.setDescription(role.description);
        return roleForm;
    }

    @GetMapping("/{id}")
    public String getTasks(
            Model model,
            @PathVariable int id,
            @RequestParam(required = false) boolean isNew,
            @RequestParam(required = false) boolean isUpdated
            ) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isNew", isNew);
        model.addAttribute("isUpdated", isUpdated);

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
