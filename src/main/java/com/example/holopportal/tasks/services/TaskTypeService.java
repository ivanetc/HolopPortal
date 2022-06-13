package com.example.holopportal.tasks.services;

import java.util.List;

import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.tasks.repository.TaskTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeService {

    TaskTypeRepo taskTypeRepo;

    @Autowired
    TaskTypeService(TaskTypeRepo taskTypeRepo) {
        this.taskTypeRepo = taskTypeRepo;
    }

    public List<TaskType> getAllTaskTypes() {
        return taskTypeRepo.findAll();
    }

}
