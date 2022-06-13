package com.example.holopportal.tasks.repository;

import com.example.holopportal.tasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {

}