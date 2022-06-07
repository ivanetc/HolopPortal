package com.example.holopportal.tasks.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class TasksService {
    List<TaskType> types;
    List<Task> tasks;

    @Inject
    UserService userService;

    public TasksService(UserService userService) {
        types = new ArrayList<>();
        types.add(new TaskType(0, "Новая роль"));
        types.add(new TaskType(1, "Задание на сценарий"));
        types.add(new TaskType(2, "Задание на декорации"));

        tasks = new ArrayList<>();
        tasks.add(
                new Task(0,
                        types.get(1),
                        "Создать сценарий на первую встречу перевоспитуемого",
                        "welcome-1",
                        loremIpsum,
                        userService.getAllWorkers()
                )
        );
        tasks.add(
                new Task(1,
                        types.get(1),
                        "Сыграть сцену из сценарий",
                        "welcome-2",
                        loremIpsum,
                        userService.getAllWorkers()
                )
        );
    }

    public List<TaskType> getAllTaskTypes() {
        return types;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }


    private final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
}
