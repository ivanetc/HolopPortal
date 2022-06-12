package com.example.holopportal.controllers.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.management.InstanceNotFoundException;
import javax.swing.text.html.Option;

import com.example.holopportal.screenplay.services.ScreenPlayService;
import com.example.holopportal.tasks.services.TasksService;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksRestController {
    @Inject
    UserService userService;

    @Inject
    TasksService tasksService;

    @Inject
    ScreenPlayService screenPlayService;

    @PutMapping("/api/tasks/{id}/status")
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
}
