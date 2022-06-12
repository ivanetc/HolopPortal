package com.example.holopportal.user.repository;

import java.util.List;

import com.example.holopportal.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByLogin(String login);

    User findById(long id);
}