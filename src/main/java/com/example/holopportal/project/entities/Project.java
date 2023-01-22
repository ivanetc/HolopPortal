package com.example.holopportal.project.entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int id;

    @Column(name = "lastName")
    public String firstName;

    @Column(name = "firstName")
    public String lastName;
    @Column(name = "age")
    public Integer age;
    @Column(name = "loveImpactValue")
    public Integer loveImpactValue;
    @Column(name = "honestImpactValue")
    public Integer honestImpactValue;
    @Column(name = "kindnessImpactValue")
    public Integer kindnessImpactValue;
    @Column(name = "wishes")
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
