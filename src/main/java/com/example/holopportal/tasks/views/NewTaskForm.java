package com.example.holopportal.tasks.views;

import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.user.entities.User;

import java.util.List;

public class NewTaskForm {
    private String name;
    private String code;
    private String description;
    private TaskType taskType;
    private List<User> workers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }
}
