package com.example.holopportal.tasks.entities;

public enum TaskExecutionStatus {
    WaitingForStart(0, "Ожидает начала"),
    WaitingForConfirmation(1, "Ожидает подтверждения"),
    InWork(2, "Выполняется"),
    Successful(3, "Успешно завершено"),
    Failed(4, "Неуспешно завершено"),
    Canceled(5, "Отменена режиссером");

    public final String description;
    public final int id;

    TaskExecutionStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }
}


