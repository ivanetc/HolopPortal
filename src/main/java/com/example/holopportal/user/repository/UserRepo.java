package com.example.holopportal.user.repository;

import com.example.holopportal.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    List<User> findByLogin(String login);

    User findById(int id);

    Optional<User> findByTelegramLogin(String telegramLogin);
}