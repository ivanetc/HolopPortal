package com.example.holopportal.project.entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int id;
    public String firstName;
    public String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
