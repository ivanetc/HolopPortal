package com.example.holopportal.tasks.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "task_execution_statuses")
public class TaskExecutionStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String description;

    protected TaskExecutionStatus() {}

    TaskExecutionStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public enum DefaultStatusIds {
        WaitingForStart(1),
        WaitingForConfirmation(2),
        InWork(3),
        Successful(4),
        Failed(5),
        Canceled(6);

        public final int id;

        DefaultStatusIds(int id) {this.id = id;}

        public int getId() {
            return id;
        }
    }
}


