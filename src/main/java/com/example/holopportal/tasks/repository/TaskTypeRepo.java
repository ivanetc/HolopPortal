package com.example.holopportal.tasks.repository;

import com.example.holopportal.tasks.entities.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepo extends JpaRepository<TaskType, Integer> {

}