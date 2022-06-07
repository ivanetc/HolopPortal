package com.example.holopportal.tasks.entities;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.holopportal.user.entities.User;

public class WorkerTaskExecutionStatus {
    public User worker;
    public TaskExecutionStatus taskExecutionStatus;
    public LocalDateTime createdAt;

    public WorkerTaskExecutionStatus(User worker, TaskExecutionStatus taskExecutionStatus) {
        this.worker = worker;
        this.taskExecutionStatus = taskExecutionStatus;
        this.createdAt = LocalDateTime.now();
    }
}
