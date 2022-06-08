package com.example.holopportal.tasks.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.management.InstanceNotFoundException;

import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.entities.TaskExecutionStatus;
import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.tasks.entities.WorkerTaskExecutionStatus;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Component;
import sun.tools.jconsole.Worker;

@Component
public class TasksService {
    List<TaskType> types;
    List<Task> tasks;

    @Inject
    UserService userService;

    public TasksService(UserService userService) {
        this.userService = userService;
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
                        getWorkerTaskExecutionStatusesWaiting()
                )
        );
        tasks.add(
                new Task(1,
                        types.get(1),
                        "Сыграть сцену из сценарий",
                        "welcome-2",
                        loremIpsum,
                        getWorkerTaskExecutionStatusesDifferent()
                )
        );
    }

    private List<WorkerTaskExecutionStatus> getWorkerTaskExecutionStatusesWaiting() {
        List<WorkerTaskExecutionStatus> result = new ArrayList<>();
        for (User worker :
                userService.getAllWorkers()) {
            result.add(new WorkerTaskExecutionStatus(worker, TaskExecutionStatus.WaitingForStart));
        }
        return result;
    }

    private List<WorkerTaskExecutionStatus> getWorkerTaskExecutionStatusesDifferent() {
        List<WorkerTaskExecutionStatus> result = new ArrayList<>();
        for (User worker :
                userService.getAllWorkers()) {
            switch (worker.id) {
                case 0:
                    result.add(new WorkerTaskExecutionStatus(worker, TaskExecutionStatus.Successful));
                    break;
                case 1:
                    result.add(new WorkerTaskExecutionStatus(worker, TaskExecutionStatus.InWork));
                    break;
                case 2:
                    result.add(new WorkerTaskExecutionStatus(worker, TaskExecutionStatus.Failed));
                    break;
                default:
                    result.add(new WorkerTaskExecutionStatus(worker, TaskExecutionStatus.WaitingForConfirmation));
                    break;
            }
        }

        return result;
    }

    public List<TaskType> getAllTaskTypes() {
        return types;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void setStatus(User user, int taskId, int statusId) throws InstanceNotFoundException {
        Task task = getTaskById(taskId).orElseThrow(InstanceNotFoundException::new);
        TaskExecutionStatus status = getStatusById(statusId).orElseThrow(InstanceNotFoundException::new);

        //remove prev status
        task.executionStatuses.stream()
            .filter(workerStatus -> workerStatus.worker.id == user.id)
            .findFirst()
            .ifPresent(workerTaskExecutionStatus -> task.executionStatuses.remove(workerTaskExecutionStatus));

        task.executionStatuses.add(new WorkerTaskExecutionStatus(user, status));
    }

    public Optional<Task> getTaskById(int taskId) {
        return tasks.stream().filter(task -> task.id == taskId).findFirst();
    }

    public Optional<TaskExecutionStatus> getStatusById(int statusId) {
        return Arrays.stream(TaskExecutionStatus.values())
                .filter(status -> status.id == statusId)
                .findFirst();
    }


    private final String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
}
