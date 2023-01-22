package com.example.holopportal.screenplay.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.Role;
import com.example.holopportal.screenplay.repository.RoleRepo;
import com.example.holopportal.screenplay.views.RoleAssignmentForm;
import com.example.holopportal.screenplay.views.RoleForm;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RoleService {

    @Inject
    RoleRepo roleRepo;

    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    public Optional<Role> getRoleById(int id) {
        return roleRepo.findById(id);
    }

    public Role createRole(RoleForm roleForm) {
        Role role = new Role();
        mapToRole(role, roleForm);
        return roleRepo.save(role);
    }

    public Optional<Role> updateRole(RoleForm roleForm) {
        Optional<Role> optionalRole = roleRepo.findById(roleForm.getId());
        if (!optionalRole.isPresent()) {
            return Optional.empty();
        }

        Role role = optionalRole.get();
        mapToRole(role, roleForm);
        return Optional.of(roleRepo.save(role));
    }

    public Optional<Role> assignToRole(RoleAssignmentForm assignmentForm) {
        Optional<Role> optionalRole = roleRepo.findById(assignmentForm.getRoleId());
        if (!optionalRole.isPresent()) {
            return Optional.empty();
        }

        Role role = optionalRole.get();
        role.actor = assignmentForm.getActor();

        return Optional.of(roleRepo.save(role));
    }

    private static Role mapToRole(Role role, RoleForm roleForm) {
        role.name = roleForm.getName();
        role.description = roleForm.getDescription();
        return role;
    }
}
