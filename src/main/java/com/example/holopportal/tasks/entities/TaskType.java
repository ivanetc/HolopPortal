package com.example.holopportal.tasks.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "task_types")
public class TaskType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String description;

    protected TaskType() {}

    public TaskType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
