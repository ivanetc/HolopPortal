package com.example.holopportal.controllers.rest;

import com.example.holopportal.project.entities.Project;
import com.example.holopportal.project.repository.ProjectRepo;
import com.example.holopportal.project.services.ProjectService;
import com.example.holopportal.project.views.NewProjectForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;

import javax.inject.Inject;
import java.util.Optional;

@Controller
@RequestMapping("/api/project")

public class ProjectRestController {
    @Inject
    ProjectService projectService;

    @Inject
    ProjectRepo projectRepo;

    @PostMapping()
    public RedirectView createProject(Model model, @ModelAttribute NewProjectForm newProjectForm){
        Optional<Project> createdProject = projectService.createProject(newProjectForm);

        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);

        if (createdProject.isPresent()) {
            redirectView.setUrl("/info/" + createdProject.get().id + "?isNew=true" );
        } else {
            redirectView.setUrl("/info");
        }

        return redirectView;
    }
}
