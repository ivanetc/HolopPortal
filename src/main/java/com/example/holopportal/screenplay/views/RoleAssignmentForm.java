package com.example.holopportal.screenplay.views;

import com.example.holopportal.user.entities.User;

public class RoleAssignmentForm {
    private int roleId;

    private String roleName;

    private User actor;

    public RoleAssignmentForm() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }
}
