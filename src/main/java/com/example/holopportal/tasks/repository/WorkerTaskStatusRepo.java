package com.example.holopportal.tasks.repository;

import com.example.holopportal.tasks.entities.WorkerTaskExecutionStatus;
import org.springframework.data.repository.CrudRepository;

public interface WorkerTaskStatusRepo extends CrudRepository<WorkerTaskExecutionStatus, Integer> {

}