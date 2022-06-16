package com.example.holopportal.prediction;

import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.entities.TaskExecutionStatus;
import com.example.holopportal.tasks.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PredictionService {
    TasksService tasksService;
    private final int loveTarget;
    private final int kindnessTarget;
    private final int honestyTarget;

    @Autowired
    PredictionService(TasksService tasksService) {
        this.tasksService = tasksService;
        loveTarget = 10;
        kindnessTarget = 10;
        honestyTarget = 10;
    }

    public PredictionView getCurrentPrediction() {
        int currentLoveValue = 0;
        int currentKindnessValue = 0;
        int currentHonestyValue = 0;

        for (Task task : tasksService.getAllTasks()) {
            if (task.getCommonStatus().id == TaskExecutionStatus.DefaultStatusIds.Successful.getId()) {
                currentLoveValue += task.loveImpactValue;
                currentKindnessValue += task.kindnessImpactValue;
                currentHonestyValue += task.honestImpactValue;
            }
        }
        // здесь скалькулировать процент выполнения
        return new PredictionView(currentLoveValue, currentKindnessValue, currentHonestyValue, loveTarget,
                kindnessTarget, honestyTarget);
    }
}
