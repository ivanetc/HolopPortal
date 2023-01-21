package com.example.holopportal.controllers.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.management.InstanceNotFoundException;

import com.example.holopportal.screenplay.services.ScreenplayService;
import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.repository.TaskRepo;
import com.example.holopportal.tasks.services.TasksService;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/tasks")
public class TasksRestController {
    @Inject
    UserService userService;

    @Inject
    TasksService tasksService;

    @Inject
    TaskRepo taskRepo;

    @Inject
    ScreenplayService screenPlayService;

    @PutMapping("/{id}/status")
    public ResponseEntity<?> setStatus(@PathVariable("id") int taskid,
                                       @RequestParam int statusId) throws InstanceNotFoundException {
        Optional<User> currentUser = userService.getCurrentUser();
        if (currentUser.isPresent()) {
            tasksService.setStatus(currentUser.get(), taskid, statusId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public RedirectView createTask(Model model, @ModelAttribute NewTaskForm newTaskForm) {

        Optional<Task> createdTask = tasksService.createTask(newTaskForm);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        if (createdTask.isPresent()) {
            redirectView.setUrl("/tasks/" + createdTask.get().id + "?isNew=true" );
        } else {
            redirectView.setUrl("/tasks");
        }

        return redirectView;
    }

    @PostMapping("/{id}/edit")
    public String taskUpdate(@RequestParam String name,
                             @RequestParam String code,
                             @RequestParam String description,
                             @RequestParam int kindnessImpactValue,
                             @RequestParam int loveImpactValue,
                             @RequestParam int honestImpactValue,
                             @PathVariable int id,
                             Model model){
        Task task = taskRepo.findById(id).orElseThrow();
        task.setName(name);
        task.setCode(code);
        task.setDescription(description);
        task.setKindnessImpactValue(kindnessImpactValue);
        task.setLoveImpactValue(loveImpactValue);
        task.setHonestImpactValue(honestImpactValue);
        taskRepo.save(task);
        return "redirect:/tasks";
    }
}
