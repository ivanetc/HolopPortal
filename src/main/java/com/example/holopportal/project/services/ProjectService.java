package com.example.holopportal.project.services;

import com.example.holopportal.project.entities.Project;
import com.example.holopportal.project.repository.ProjectRepo;
import com.example.holopportal.project.views.NewProjectForm;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectService {

    ProjectRepo projectRepo;

    public Optional<Project> createProject(NewProjectForm newProjectForm){
        Project newProject = new Project();

        newProject.first_name = newProjectForm.getFirst_name();
        newProject.last_name = newProjectForm.getLast_name();
        newProject.age = newProjectForm.getAge();
        newProject.wishes = newProjectForm.getWishes();
        newProject.honestImpactValue = newProjectForm.getHonestImpactValue();
        newProject.kindnessImpactValue = newProjectForm.getKindnessImpactValue();
        newProject.loveImpactValue = newProjectForm.getLoveImpactValue();

        Project savedProject = projectRepo.save(newProject);

        return Optional.of(savedProject);
    }
}
