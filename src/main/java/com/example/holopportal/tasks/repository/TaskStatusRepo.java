package com.example.holopportal.tasks.repository;

import com.example.holopportal.tasks.entities.TaskExecutionStatus;
import org.springframework.data.repository.CrudRepository;

public interface TaskStatusRepo extends CrudRepository<TaskExecutionStatus, Integer> {

}
