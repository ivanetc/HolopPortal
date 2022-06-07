package com.example.holopportal.tasks.entities;

import java.util.List;

import com.example.holopportal.user.entities.User;

public class Task {
    public String taskName;
    public String taskCode;
    public int id;
    public String description;
    public TaskType taskType;

    public List<User> staff;

    public Task(int id, TaskType taskType, String taskName, String taskCode, String description, List<User> staff) {
        this.id = id;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskCode = taskCode;
        this.description = description;
        this.staff = staff;
    }
}
