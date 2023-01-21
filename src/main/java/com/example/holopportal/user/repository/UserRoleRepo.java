package com.example.holopportal.user.repository;

import java.util.List;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {

}