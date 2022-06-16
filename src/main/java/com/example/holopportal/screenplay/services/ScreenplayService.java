package com.example.holopportal.screenplay.services;

import java.util.List;

import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.repository.ScreenplayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenplayService {

    ScreenplayRepo screenplayRepo;

    @Autowired
    public ScreenplayService(ScreenplayRepo screenPlayRepo) {
        this.screenplayRepo = screenPlayRepo;
    }

    public List<Screenplay> getAllScreenplays() {
        return screenplayRepo.findAll();
    }
}
