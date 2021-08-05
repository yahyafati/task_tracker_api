package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String issue;
    private String description;
    private LocalDateTime addedDate;
    private LocalDate dueDate;

    @ManyToMany
    @JsonIgnore @ToString.Exclude
    private Set<Person> persons = new HashSet<>();
    @ManyToOne
    private Priority priority;

}
