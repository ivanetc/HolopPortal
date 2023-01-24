package com.example.holopportal.user.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String description;

    public String code;

    protected UserRole() {}

    UserRole(int id, String code, String description) {
        this.id = id;
        this.description = description;
        this.code = code;
    }

    public enum DefaultUserRoles {

        WORKER(1),
        DIRECTOR(2),
        SCREEN_WRITER(3),
        REQUESTER(4);

        public final int id;

        DefaultUserRoles(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
