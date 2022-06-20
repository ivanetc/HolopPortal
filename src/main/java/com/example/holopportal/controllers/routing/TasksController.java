package com.example.holopportal.controllers.routing;

import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.services.ScreenplayService;
import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.services.TaskTypeService;
import com.example.holopportal.tasks.services.TasksService;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    @Inject
    UserService userService;

    @Inject
    TasksService tasksService;

    @Inject
    TaskTypeService taskTypeService;

    @Inject
    ScreenplayService screenPlayService;

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("all_workers", userService.getAllWorkers());
        model.addAttribute("taskTypes", taskTypeService.getAllTaskTypes());
        model.addAttribute("allSreenplays", screenPlayService.getAllScreenplays());
        model.addAttribute("newTaskForm", new NewTaskForm());
        return "newtask";
    }

    @GetMapping()
    public String getTasks(Model model) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("tasks", tasksService.getAllUserTasks(currentUser));

        return "tasks";
    }

    private User getCurrentUser() {
        Optional<User> currentUser = userService.getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "please login");
        }

        return currentUser.get();
    }

    @GetMapping("/{id}")
    public String getTasks(Model model, @PathVariable int id, @RequestParam(required = false) boolean isNew) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isNew", isNew);

        Optional<Task> task = tasksService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "fragments/singleTask";
        } else {
            model.addAttribute("tasks", tasksService.getAllUserTasks(currentUser));
            return "tasks";
        }
    }


}
