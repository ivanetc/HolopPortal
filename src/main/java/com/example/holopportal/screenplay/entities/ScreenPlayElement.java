package com.example.holopportal.screenplay.entities;

public class ScreenPlayElement {
    public String code;
    public String name;
    public int id;

    public ScreenPlayElement(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}
