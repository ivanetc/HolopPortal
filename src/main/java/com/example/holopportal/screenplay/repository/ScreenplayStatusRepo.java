package com.example.holopportal.screenplay.repository;

import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.entities.ScreenplayStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenplayStatusRepo extends JpaRepository<ScreenplayStatus, Integer> {
}
