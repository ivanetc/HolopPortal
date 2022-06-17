package com.example.holopportal.screenplay.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.holopportal.tasks.entities.Task;

@Entity
@Table(schema = "public", name = "screenplays")
public class Screenplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int id;

    public String code;
    public String name;
    public String content;

    @OneToMany(mappedBy = "screenplay", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Task> tasks;

    public Screenplay() {
        tasks = new HashSet<>();
    }
}
