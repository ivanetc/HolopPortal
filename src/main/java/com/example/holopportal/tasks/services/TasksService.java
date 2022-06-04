package com.example.holopportal.tasks.services;

import java.util.ArrayList;
import java.util.List;

import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.user.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TasksService {

    public List<TaskType> getAllTaskTypes() {
        List<TaskType> types = new ArrayList<>();
        types.add(new TaskType(0, "Новая роль"));
        types.add(new TaskType(1, "Задание на сценарий"));
        types.add(new TaskType(0, "Задание на декорации"));
        return types;
    }
}
