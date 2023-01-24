package com.example.holopportal.screenplay.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.InstanceNotFoundException;
import javax.swing.text.html.Option;

import com.example.holopportal.screenplay.entities.Screenplay;
import com.example.holopportal.screenplay.entities.ScreenplayStatus;
import com.example.holopportal.screenplay.repository.ScreenplayRepo;
import com.example.holopportal.screenplay.repository.ScreenplayStatusRepo;
import com.example.holopportal.screenplay.views.ScreenplayForm;
import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.entities.TaskExecutionStatus;
import com.example.holopportal.tasks.entities.WorkerTaskExecutionStatus;
import com.example.holopportal.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ScreenplayService {

    ScreenplayRepo screenplayRepo;

    ScreenplayStatusRepo screenplayStatusRepo;

    @Autowired
    public ScreenplayService(ScreenplayRepo screenPlayRepo, ScreenplayStatusRepo screenplayStatusRepo) {
        this.screenplayRepo = screenPlayRepo;
        this.screenplayStatusRepo = screenplayStatusRepo;
    }

    public Optional<Screenplay> createNewScreenplay(ScreenplayForm screenplayForm, User currentUser) {
        ScreenplayStatus draftStatus = screenplayStatusRepo.findById(ScreenplayStatus.DefaultStatusIds.Draft.id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "status not found"));

        if (screenplayForm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no data provided");
        }

        Screenplay newScreenplay = new Screenplay();

        newScreenplay.code = screenplayForm.getCode();
        newScreenplay.content = screenplayForm.getContent();
        newScreenplay.name = screenplayForm.getName();
        newScreenplay.author = currentUser;
        newScreenplay.status = draftStatus;

        return Optional.of(screenplayRepo.save(newScreenplay));
    }

    public void setStatus(int screenplayId, int statusId) throws InstanceNotFoundException {
        Screenplay screenplay = screenplayRepo.findById(screenplayId)
                .orElseThrow(InstanceNotFoundException::new);

        screenplay.status = screenplayStatusRepo.findById(statusId)
                .orElseThrow(InstanceNotFoundException::new);
        screenplayRepo.save(screenplay);
    }

    public List<Screenplay> getAllScreenplays() {
        return screenplayRepo.findAll();
    }

    public List<Screenplay> getAllScreenplaysWithStatus(ScreenplayStatus.DefaultStatusIds screenplayStatusId) {
        ScreenplayStatus status = screenplayStatusRepo.findById(screenplayStatusId.id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "status not found"));

        return screenplayRepo.findAllByStatusId(status.id);
    }

    public Optional<Screenplay> getScreenplayById(int id) {
        return screenplayRepo.findById(id);
    }
}
