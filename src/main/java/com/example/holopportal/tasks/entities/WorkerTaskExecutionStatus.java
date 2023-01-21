package com.example.holopportal.tasks.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.example.holopportal.user.entities.User;

@Entity
@Table(schema = "public", name = "worker_task_execution_statuses")
public class WorkerTaskExecutionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @OneToOne
    public User worker;

    @OneToOne
    public TaskExecutionStatus taskExecutionStatus;

    @ManyToOne
    @JoinColumn(name="task_id", nullable=false)
    public Task task;

    public LocalDateTime createdAt;

    public WorkerTaskExecutionStatus() {}

    public WorkerTaskExecutionStatus(User worker, Task task, TaskExecutionStatus taskExecutionStatus) {
        this.worker = worker;
        this.taskExecutionStatus = taskExecutionStatus;
        this.createdAt = LocalDateTime.now();
        this.task = task;
    }

    public User getWorker() {
        return worker;
    }

    @PreRemove
    private void removeFromTask() {
        task.workerStatuses.remove(this);
    }
}
