package com.example.holopportal.controllers;

import javax.inject.Inject;

import com.example.holopportal.screenplay.services.ScreenPlayService;
import com.example.holopportal.tasks.services.TaskTypeService;
import com.example.holopportal.tasks.services.TasksService;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasksController {
    @Inject
    UserService userService;

    @Inject
    TasksService tasksService;

    @Inject
    TaskTypeService taskTypeService;

    @Inject
    ScreenPlayService screenPlayService;

    @GetMapping("/tasks/new")
    public String greeting(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("workers", userService.getAllWorkers());
        model.addAttribute("taskTypes", taskTypeService.getAllTaskTypes());
        model.addAttribute("screenPlayElements", screenPlayService.getAllScreenPlays());
        model.addAttribute("newTaskForm", new NewTaskForm());
        return "newtask";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("tasks", tasksService.getAllTasks());
        return "tasks";
    }


}
