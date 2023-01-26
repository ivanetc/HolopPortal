package com.example.holopportal.project.services;

import com.example.holopportal.project.entities.Project;
import com.example.holopportal.project.repository.ProjectRepo;
import com.example.holopportal.project.views.NewProjectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Optional;

@Component
public class ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    public Optional<Project> createProject(NewProjectForm newProjectForm){
        Project newProject = new Project();

        newProject.firstName = newProjectForm.getFirstName();
        newProject.lastName = newProjectForm.getLastName();
        newProject.age = newProjectForm.getAge();
        newProject.wishes = newProjectForm.getWishes();
        newProject.honestImpactValue = newProjectForm.getHonestImpactValue();
        newProject.kindnessImpactValue = newProjectForm.getKindnessImpactValue();
        newProject.loveImpactValue = newProjectForm.getLoveImpactValue();

        Project savedProject = projectRepo.save(newProject);

        return Optional.of(savedProject);
    }
}
