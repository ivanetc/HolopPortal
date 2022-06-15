package com.example.holopportal.tasks.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int id;

    public String name;
    public String code;
    public String description;
    public Integer loveImpactValue;
    public Integer honestImpactValue;
    public Integer kindnessImpactValue;

    @OneToOne
    public TaskType taskType;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<WorkerTaskExecutionStatus> workerStatuses;

    public Task() {
        workerStatuses = new HashSet<>();
        loveImpactValue = 0;
        honestImpactValue = 0;
        kindnessImpactValue = 0;
    }
}
