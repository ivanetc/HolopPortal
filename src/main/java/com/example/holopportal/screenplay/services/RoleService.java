package com.example.holopportal.screenplay.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.Role;
import com.example.holopportal.screenplay.repository.RoleRepo;
import org.springframework.stereotype.Component;

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
}
