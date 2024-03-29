package com.example.holopportal.screenplay.repository;

import java.util.List;

import com.example.holopportal.screenplay.entities.Screenplay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenplayRepo extends JpaRepository<Screenplay, Integer> {
    List<Screenplay> findAllByStatusId(int status_id);
}
