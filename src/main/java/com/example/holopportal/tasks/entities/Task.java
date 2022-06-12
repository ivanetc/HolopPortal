package com.example.holopportal.tasks.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(schema = "public", name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String name;
    public String code;
    public String description;

    @OneToOne
    public TaskType taskType;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<WorkerTaskExecutionStatus> workerStatuses;

    public Task() {}

    @Deprecated
    public Task(int id, TaskType taskType, String taskName, String taskCode, String description,
                List<WorkerTaskExecutionStatus> executionStatuses) {
        this.id = id;
        this.taskType = taskType;
        this.name = taskName;
        this.code = taskCode;
        this.description = description;
    }
}
