package com.example.holopportal.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.entities.UserRole;
import com.example.holopportal.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        setDefaultPasswords(this.userRepo.findAll());
    }

    private void setDefaultPasswords(Iterable<User> users) {
        for (User user : users) {
            String password = user.getPassword();
            if (password == null || password.isEmpty()) {
                user.setPassword(bCryptPasswordEncoder.encode("password"));
                userRepo.save(user);
            }
        }
    }

    public List<User> getAllWorkers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepo.findByLogin(username).stream()
                .findFirst()
                .orElse(null);

        if (foundUser == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return foundUser;
    }

    public Optional<User> getCurrentUser() {
        Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            return Optional.of(user);
        }

        return Optional.empty();
    }
}
