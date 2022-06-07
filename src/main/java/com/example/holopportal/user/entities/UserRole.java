package com.example.holopportal.user.entities;

public enum UserRole {
    Worker(0, "WORKER", "Сотрудник"),
    Director(1,"DIRECTOR", "Режиссер"),
    ScreenWriter(2,"SCREEN_WRITER", "Сценарист"),
    Requester(3,"REQUESTER", "Заказчик");

    public final String description;
    public final String code;
    public final int id;

    UserRole(int id, String code, String description) {
        this.id = id;
        this.description = description;
        this.code = code;
    }
}
