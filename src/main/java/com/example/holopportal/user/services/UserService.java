package com.example.holopportal.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.holopportal.user.entities.User;
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
        User testUser1 = new User("user", "Alexander", "Ivanets", 0);
        testUser1.setPassword(bCryptPasswordEncoder.encode("password"));
        users.add(testUser1);
    }

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllWorkers() {
        List<User> workers = new ArrayList<>();
        workers.add(new User("ivanetsas", "Alexander", "Ivanets", 0));
        workers.add(new User("KhilikE", "Egor", "Khilik", 1));
        workers.add(new User("NosovaS", "Svetlana", "Nosova", 2));

        return workers;
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
