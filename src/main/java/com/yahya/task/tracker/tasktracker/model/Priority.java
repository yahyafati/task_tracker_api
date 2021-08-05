package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Integer level;

    @OneToMany(mappedBy = "priority")
    @JsonIgnore @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();
}
