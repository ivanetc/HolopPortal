package com.example.holopportal.controllers;

import javax.inject.Inject;

import com.example.holopportal.screenplay.entities.ScreenPlayElement;
import com.example.holopportal.screenplay.services.ScreenPlayService;
import com.example.holopportal.tasks.services.TasksService;
import com.example.holopportal.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TasksController {
    @Inject
    UserService userService;

    @Inject
    TasksService tasksService;

    @Inject
    ScreenPlayService screenPlayService;

    @GetMapping("/tasks/new")
    public String greeting(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("workers", userService.getAllWorkers());
        model.addAttribute("taskTypes", tasksService.getAllTaskTypes());
        model.addAttribute("screenPlayElements", screenPlayService.getAllScreenPlays());
        return "newtask";
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("tasks", tasksService.getAllTasks());

        System.out.println(
                userService.getCurrentUser().get().getAuthorities().stream().findFirst().get().getAuthority()
        );
        return "tasks";
    }
}
