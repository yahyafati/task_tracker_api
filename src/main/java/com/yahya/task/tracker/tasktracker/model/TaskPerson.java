package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class TaskPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    private Task task;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Person person;
    private boolean isLeader;

    public TaskPerson(Task task, Person person, boolean isLeader) {
        this.task = task;
        this.person = person;
        this.isLeader = isLeader;
    }

}
