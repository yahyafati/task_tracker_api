package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnore
    private Task task;
    @ManyToOne
    private Profile profile;
    private boolean isLeader;
    private boolean hasVisited;

    public TaskPerson(Task task, Profile profile, boolean isLeader) {
        this.task = task;
        this.profile = profile;
        this.isLeader = isLeader;
    }

}
