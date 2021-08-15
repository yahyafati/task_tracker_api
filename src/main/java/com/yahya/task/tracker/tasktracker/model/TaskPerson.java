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
    private Person person;
    private boolean isLeader;

    public TaskPerson(Task task, Person person, boolean isLeader) {
        this.task = task;
        this.person = person;
        this.isLeader = isLeader;
    }

}
