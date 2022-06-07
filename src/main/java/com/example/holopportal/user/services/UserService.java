package com.example.holopportal.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.entities.UserRole;
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

    private List<User> users;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        users = new ArrayList<>();
        users.add(new User("ivanetsas", "Alexander", "Ivanets", 0, UserRole.Director));
        users.add(new User("khilike", "Egor", "Khilik", 1, UserRole.Worker));
        users.add(new User("nosovas", "Svetlana", "Nosova", 2, UserRole.ScreenWriter));
        users.get(0).setPassword(bCryptPasswordEncoder.encode("password"));
        users.get(1).setPassword(bCryptPasswordEncoder.encode("password"));
        users.get(2).setPassword(bCryptPasswordEncoder.encode("password"));
    }

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllWorkers() {
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = users.stream()
                            .filter(user -> Objects.equals(user.login, username))
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
