package com.example.holopportal.screenplay.services;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.repository.ScreenplayRepo;
import com.example.holopportal.screenplay.views.ScreenplayForm;
import com.example.holopportal.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenplayService {

    ScreenplayRepo screenplayRepo;

    @Autowired
    public ScreenplayService(ScreenplayRepo screenPlayRepo) {
        this.screenplayRepo = screenPlayRepo;
    }

    public Screenplay createNewScreenplay(ScreenplayForm screenplayForm, User currentUser) {
        Screenplay newScreenplay = new Screenplay();

        newScreenplay.code = screenplayForm.getCode();
        newScreenplay.content = screenplayForm.getContent();
        newScreenplay.name = screenplayForm.getName();


        return screenplayRepo.save(newScreenplay);
    }

    public List<Screenplay> getAllScreenplays() {
        return screenplayRepo.findAll();
    }
}
