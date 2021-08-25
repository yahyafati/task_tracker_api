package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class TaskPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnore
    private Task task;
    @ManyToOne
    private UserProfile userProfile;
    private boolean isLeader;
    private boolean hasVisited;

    public TaskPerson(Task task, UserProfile userProfile, boolean isLeader) {
        this.task = task;
        this.userProfile = userProfile;
        this.isLeader = isLeader;
    }

}
