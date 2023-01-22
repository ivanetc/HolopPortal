package com.example.holopportal.screenplay.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.holopportal.user.entities.User;

@Entity
@Table(schema = "public", name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int id;

    public String name;

    public String description;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    public User actor;

    public String getActorName() {
        if (actor == null) {
            return "Не назначен";
        }

        return String.format("%s %s", actor.firstName, actor.lastName);
    }
}
