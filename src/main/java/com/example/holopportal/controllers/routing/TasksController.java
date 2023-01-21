package com.example.holopportal.controllers.routing;

import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;

import com.example.holopportal.screenplay.services.ScreenplayService;
import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.repository.TaskRepo;
import com.example.holopportal.tasks.services.TaskTypeService;
import com.example.holopportal.tasks.services.TasksService;
import com.example.holopportal.tasks.views.NewTaskForm;
import com.example.holopportal.user.entities.User;
import com.example.holopportal.user.services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

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
    public String getTasks(Model model, @PathVariable int id,
                           @RequestParam(required = false) boolean isNew,
                           @RequestParam(required = false) boolean isUpdated) {
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("isNew", isNew);
        model.addAttribute("isUpdated", isUpdated);

        Optional<Task> task = tasksService.getTaskById(id);
        if (task.isPresent()) {
            model.addAttribute("task", task.get());
            return "fragments/singleTask";
        } else {
            model.addAttribute("tasks", tasksService.getAllUserTasks(currentUser));
            return "tasks";
        }
    }
    @GetMapping("/{id}/edit")
    public String taskEdit(Model model, @PathVariable int id){
        model.addAttribute("currentUser", userService.getCurrentUser().get());
        model.addAttribute("all_workers", userService.getAllWorkers());
        model.addAttribute("taskTypes", taskTypeService.getAllTaskTypes());
        model.addAttribute("allSreenplays", screenPlayService.getAllScreenplays());
        User currentUser = getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        Optional<Task> taskOptional = tasksService.getTaskById(id);

        if (!taskOptional.isPresent()) {
            model.addAttribute("newTaskForm", new NewTaskForm());
            return "redirect:/tasks";
        }

        Task task = taskOptional.get();
        model.addAttribute("editTaskForm", mapToForm(task)); //todo do you need a new form?

        ArrayList<Task> res = new ArrayList<>();
        taskOptional.ifPresent(res::add);

        model.addAttribute("task", res);
        return "taskEdit";
    }

    @NotNull
    private static NewTaskForm mapToForm(Task task) {
        NewTaskForm newTaskForm = new NewTaskForm();
        newTaskForm.setId(task.id);
        newTaskForm.setName(task.name);
        newTaskForm.setTaskType(task.taskType);
        newTaskForm.setScreenplay(task.screenplay);
        newTaskForm.setCode(task.code);
        newTaskForm.setDescription(task.description);
        newTaskForm.setWorkers(task.getWorkers());
        newTaskForm.setHonestImpactValue(task.honestImpactValue);
        newTaskForm.setKindnessImpactValue(task.kindnessImpactValue);
        newTaskForm.setLoveImpactValue(task.loveImpactValue);
        return newTaskForm;
    }

}
