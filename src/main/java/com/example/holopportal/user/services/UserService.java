package com.example.holopportal.user.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.entities.UserRole;
import com.example.holopportal.user.repository.UserRepo;
import com.example.holopportal.user.repository.UserRoleRepo;
import com.example.holopportal.user.views.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepo, UserRoleRepo userRoleRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRoleRepo = userRoleRepo;
        setDefaultPasswords(this.userRepo.findAll());
    }

    public Optional<User> createNewUser(UserForm userForm) {
        Optional<UserRole> role = userRoleRepo.findById(UserRole.DefaultUserRoles.WORKER.id); // todo guest
        User user = new User(
                userForm.login,
                userForm.firstName,
                userForm.lastName,
                bCryptPasswordEncoder.encode(userForm.password),
                role.orElseThrow(RuntimeException::new)
        );
        user.firstName = userForm.getFirstName();
        user.lastName = userForm.getLastName();
        user.login = userForm.getLogin();
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));

        userRepo.save(user);
        return Optional.of(user);
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
        return userRepo.findAllByRoleId(UserRole.DefaultUserRoles.WORKER.getId());
    }

    public List<User> getAllWorkersWithoutScreenplayRole() {
        return getAllWorkers().stream()
                .filter(worker -> worker.getScreenplayRoles() == null || worker.getScreenplayRoles().size() == 0)
                .collect(Collectors.toList());
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

    public Optional<User> findByTelegramLogin(String telegramLogin) {
        return userRepo.findByTelegramLogin(telegramLogin);
    }

    public void setUserChatId(int userId, String chatId) {
        User user = userRepo.findById(userId);
        user.setChatId(chatId);
        userRepo.save(user);
    }
}
