package com.example.holopportal.screenplay.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "screenplay_agreement_statuses")
public class ScreenplayStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int id;

    public String description;

    public enum DefaultStatusIds {
        Draft(0),
        WaitingForApproving(1),
        Approved(2),
        NotApproved(3);

        public final int id;

        DefaultStatusIds(int id) {this.id = id;}

        public int getId() {
            return id;
        }
    }
}
