package com.example.holopportal.tasks.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;

import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.entities.TaskExecutionStatus;
import com.example.holopportal.tasks.entities.WorkerTaskExecutionStatus;
import com.example.holopportal.tasks.repository.TaskRepo;
import com.example.holopportal.tasks.repository.TaskStatusRepo;
import com.example.holopportal.tasks.repository.TaskTypeRepo;
import com.example.holopportal.tasks.repository.WorkerTaskStatusRepo;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class TasksService {

    TaskRepo taskRepo;

    TaskStatusRepo taskStatusRepo;

    public WorkerTaskStatusRepo workerTaskStatusRepo;

    TaskTypeRepo taskTypeRepo;

    @Autowired
    TasksService(TaskRepo taskRepo, TaskStatusRepo taskStatusRepo, WorkerTaskStatusRepo workerTaskStatusRepo,
                 TaskTypeRepo taskTypeRepo) {
        this.taskRepo = taskRepo;
        this.taskStatusRepo = taskStatusRepo;
        this.workerTaskStatusRepo = workerTaskStatusRepo;
        this.taskTypeRepo = taskTypeRepo;
    }


    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void setStatus(User user, int taskId, int statusId) throws InstanceNotFoundException {
        Task task = getTaskById(taskId).orElseThrow(InstanceNotFoundException::new);
        TaskExecutionStatus statusToSet = getStatusById(statusId).orElseThrow(InstanceNotFoundException::new);

        List<WorkerTaskExecutionStatus> prevStatusesToRemove = getPrevStatusesToRemove(user, task);
        List<User> usersToSetStatus = prevStatusesToRemove.stream()
                .map(status -> status.worker)
                .collect(Collectors.toList());

        workerTaskStatusRepo.deleteAll(prevStatusesToRemove);
        usersToSetStatus.forEach(worker -> createAndSaveNewWorkerTaskStatus(worker, task, statusToSet));
    }

    private List<WorkerTaskExecutionStatus> getPrevStatusesToRemove(User user, Task task) {
        int userRoleId = user.getRole().id;
        if (userRoleId == UserRole.DefaultUserRoles.DIRECTOR.getId()) {
            return new ArrayList<>(task.workerStatuses);
        } else if (userRoleId == UserRole.DefaultUserRoles.WORKER.getId() ||
                userRoleId == UserRole.DefaultUserRoles.SCREEN_WRITER.getId()) {
            return Collections.singletonList(task.workerStatuses.stream()
                    .filter(workerStatus -> workerStatus.worker.getId() == user.getId())
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found")));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found");
        }
    }

    private void createAndSaveNewWorkerTaskStatus(User user, Task task, TaskExecutionStatus status) {
        WorkerTaskExecutionStatus newStatus = new WorkerTaskExecutionStatus(user, task, status);
        workerTaskStatusRepo.save(newStatus);
    }

    public Optional<Task> getTaskById(int taskId) {
        return taskRepo.findById(taskId);
    }

    public Optional<TaskExecutionStatus> getStatusById(int statusId) {
        return taskStatusRepo.findById(statusId);
    }

    public Optional<Task> createTask(NewTaskForm newTaskForm) {
        Task newTask = new Task();
        newTask.taskType = newTaskForm.taskType;
        newTask.description = newTaskForm.description;
        newTask.name = newTaskForm.name;
        newTask.code = newTaskForm.code;

        Task savedTask = taskRepo.save(newTask);
        return Optional.of(savedTask);
    }
}
