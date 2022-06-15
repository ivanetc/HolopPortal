package com.example.holopportal.tasks.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public TaskExecutionStatus getCommonStatus() {
        Optional<TaskExecutionStatus> failedStatus = workerStatuses.stream()
                .filter(workerStatus -> workerStatus.taskExecutionStatus.id == TaskExecutionStatus.DefaultStatusIds.Failed.getId())
                .map(workerStatus -> workerStatus.taskExecutionStatus)
                .findFirst();

        if (failedStatus.isPresent()) {
            return failedStatus.get();
        }

        Optional<TaskExecutionStatus> canceled = workerStatuses.stream()
                .filter(workerStatus -> workerStatus.taskExecutionStatus.id == TaskExecutionStatus.DefaultStatusIds.Canceled.getId())
                .map(workerStatus -> workerStatus.taskExecutionStatus)
                .findFirst();

        if (canceled.isPresent()) {
            return canceled.get();
        }

        Optional<TaskExecutionStatus> inWork = workerStatuses.stream()
                .filter(workerStatus -> workerStatus.taskExecutionStatus.id == TaskExecutionStatus.DefaultStatusIds.InWork.getId())
                .map(workerStatus -> workerStatus.taskExecutionStatus)
                .findFirst();

        if (inWork.isPresent()) {
            return inWork.get();
        }

        Optional<TaskExecutionStatus> waitingForStart = workerStatuses.stream()
                .filter(workerStatus -> workerStatus.taskExecutionStatus.id == TaskExecutionStatus.DefaultStatusIds.WaitingForStart.getId())
                .map(workerStatus -> workerStatus.taskExecutionStatus)
                .findFirst();

        if (waitingForStart.isPresent()) {
            return waitingForStart.get();
        }

        Optional<TaskExecutionStatus> waitingForConfirm = workerStatuses.stream()
                .filter(workerStatus -> workerStatus.taskExecutionStatus.id == TaskExecutionStatus.DefaultStatusIds.WaitingForConfirmation.getId())
                .map(workerStatus -> workerStatus.taskExecutionStatus)
                .findFirst();

        if (waitingForConfirm.isPresent()) {
            return waitingForConfirm.get();
        }

        if (workerStatuses.stream()
                .allMatch(workerStatus -> workerStatus.taskExecutionStatus.id == TaskExecutionStatus.DefaultStatusIds.WaitingForConfirmation.getId())) {
            Optional<WorkerTaskExecutionStatus> successStatus = workerStatuses.stream().findFirst();
            if (successStatus.isPresent()) {
                return successStatus.get().taskExecutionStatus;
            }
        }

        TaskExecutionStatus taskExecutionStatus = new TaskExecutionStatus();
        taskExecutionStatus.description = "Статус не задан";
        return taskExecutionStatus;
    }
}
