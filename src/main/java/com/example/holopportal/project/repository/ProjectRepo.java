package com.example.holopportal.project.repository;


import com.example.holopportal.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
}
