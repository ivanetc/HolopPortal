package com.example.holopportal.user.repository;

import java.util.List;

import com.example.holopportal.user.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {

    List<User> findByLogin(String login);

    User findById(long id);
}