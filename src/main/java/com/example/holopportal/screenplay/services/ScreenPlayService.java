package com.example.holopportal.screenplay.services;

import java.util.ArrayList;
import java.util.List;

import com.example.holopportal.screenplay.entities.ScreenPlayElement;
import com.example.holopportal.tasks.entities.TaskType;
import org.springframework.stereotype.Component;

@Component
public class ScreenPlayService {
    public List<ScreenPlayElement> getAllScreenPlays() {
        List<ScreenPlayElement> screenPlayElements = new ArrayList<>();
        screenPlayElements.add(new ScreenPlayElement(0, "Казнь за проступки", "horror-1"));
        screenPlayElements.add(new ScreenPlayElement(1, "Праздник в деревне", "holiday-1"));
        screenPlayElements.add(new ScreenPlayElement(2, "Работы в поле", "hardwork-12"));
        return screenPlayElements;
    }
}
