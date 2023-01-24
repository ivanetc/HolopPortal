package com.example.holopportal.user.entities;

import com.example.holopportal.screenplay.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(schema = "public", name = "\"Users\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    private String password;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "telegram_login")
    private String telegramLogin;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private UserRole role;

    @OneToMany(mappedBy = "actor", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Role> screenplayRoles;

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {};

    public User(String login, String firstName, String lastName, int id, UserRole role, String chatId, String telegramLogin) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.role = role;
        this.chatId = chatId;
        this.telegramLogin = telegramLogin;
    }

    public User(String login, String firstName, String lastName, String password, UserRole role) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getChatId(){
        return chatId;
    }

    public String getTelegramLogin() {
        return telegramLogin;
    }

    public void setTelegramLogin(String telegramLogin) {
        this.telegramLogin = telegramLogin;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public UserRole getRole()
    {
        return role;
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.code));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getScreenplayRoles() {
        return screenplayRoles;
    }

    public void setScreenplayRoles(List<Role> screenplayRoles) {
        this.screenplayRoles = screenplayRoles;
    }
}
