package com.example.holopportal.controllers.rest;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.Role;
import com.example.holopportal.screenplay.services.RoleService;
import com.example.holopportal.screenplay.views.RoleAssignmentForm;
import com.example.holopportal.screenplay.views.RoleForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/roles")
public class RolesRestController {
    @Inject
    RoleService roleService;

    @PostMapping()
    public RedirectView createOrUpdateRole(Model model, @ModelAttribute RoleForm roleForm) {

        Optional<Role> role;
        String redirectParameter = "";
        if (roleForm.getId() == null) {
            role = Optional.of(roleService.createRole(roleForm));
            redirectParameter = "?isNew=true";
        } else {
            role = roleService.updateRole(roleForm);
            redirectParameter = "?isUpdated=true";
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        if (role.isPresent()) {
            redirectView.setUrl("/roles/" + role.get().id + redirectParameter);
        } else {
            redirectView.setUrl("/roles");
        }

        return redirectView;
    }

    @PostMapping("/assignment")
    public RedirectView createAssignment(Model model, @ModelAttribute RoleAssignmentForm roleForm) {

        Optional<Role> role = roleService.assignToRole(roleForm);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        if (role.isPresent()) {
            redirectView.setUrl("/roles/" + role.get().id + "?isUpdated=true");
        } else {
            redirectView.setUrl("/roles");
        }

        return redirectView;
    }
}
