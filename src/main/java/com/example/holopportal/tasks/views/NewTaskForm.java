package com.example.holopportal.tasks.views;

import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.user.entities.User;

public class NewTaskForm {
    public String name;
    public String code;
    public String description;
    public TaskType taskType;
    public Iterable<User> workers;
}
