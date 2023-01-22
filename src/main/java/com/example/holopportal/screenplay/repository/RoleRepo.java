package com.example.holopportal.screenplay.repository;

import com.example.holopportal.screenplay.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
