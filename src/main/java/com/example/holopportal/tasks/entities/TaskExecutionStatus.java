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
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String description;

    protected TaskExecutionStatus() {}

    TaskExecutionStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public enum DefaultStatusIds {
        WaitingForStart(0),WaitingForConfirmation(1),
        InWork(2),
        Successful(3),
        Failed(4),
        Canceled(5);

        private final int id;

        DefaultStatusIds(int id) {this.id = id;}

        public int getId() {
            return id;
        }
    }
}


