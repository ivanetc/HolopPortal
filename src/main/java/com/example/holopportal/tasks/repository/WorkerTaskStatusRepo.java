package com.example.holopportal.tasks.repository;

import java.util.List;

import com.example.holopportal.tasks.entities.WorkerTaskExecutionStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WorkerTaskStatusRepo extends CrudRepository<WorkerTaskExecutionStatus, Integer> {
    List<WorkerTaskExecutionStatus> findAllByTaskId(int taskId);
    @Transactional
    void deleteAllByTaskIdAndWorkerId(int taskId, int wokerId);
}