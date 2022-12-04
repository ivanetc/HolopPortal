package com.example.holopportal.project.entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int id;
    public String first_name;
    public String last_name;

    public Integer age;

    public Integer loveImpactValue;
    public Integer honestImpactValue;
    public Integer kindnessImpactValue;
    public String wishes;

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getLoveImpactValue() {
        return loveImpactValue;
    }

    public void setLoveImpactValue(Integer loveImpactValue) {
        this.loveImpactValue = loveImpactValue;
    }

    public Integer getHonestImpactValue() {
        return honestImpactValue;
    }

    public void setHonestImpactValue(Integer honestImpactValue) {
        this.honestImpactValue = honestImpactValue;
    }

    public Integer getKindnessImpactValue() {
        return kindnessImpactValue;
    }

    public void setKindnessImpactValue(Integer kindnessImpactValue) {
        this.kindnessImpactValue = kindnessImpactValue;
    }
}
