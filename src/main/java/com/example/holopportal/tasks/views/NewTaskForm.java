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
    private int loveImpactValue;
    private int honestImpactValue;
    private int kindnessImpactValue;

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

    public int getLoveImpactValue() {
        return loveImpactValue;
    }

    public void setLoveImpactValue(int loveImpactValue) {
        this.loveImpactValue = loveImpactValue;
    }

    public int getHonestImpactValue() {
        return honestImpactValue;
    }

    public void setHonestImpactValue(int honestImpactValue) {
        this.honestImpactValue = honestImpactValue;
    }

    public int getKindnessImpactValue() {
        return kindnessImpactValue;
    }

    public void setKindnessImpactValue(int kindnessImpactValue) {
        this.kindnessImpactValue = kindnessImpactValue;
    }
}
