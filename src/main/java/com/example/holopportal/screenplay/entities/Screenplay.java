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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.holopportal.tasks.entities.Task;
import com.example.holopportal.tasks.entities.TaskType;
import com.example.holopportal.user.entities.User;

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

    @OneToOne
    @JoinColumn(name = "author_user_id")
    public User author;

    @OneToMany(mappedBy = "screenplay", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    public Set<Task> tasks;

    @OneToOne
    public ScreenplayStatus status;

    public User getAuthor() {
        return author;
    }

    public Screenplay() {
        tasks = new HashSet<>();
    }
}
