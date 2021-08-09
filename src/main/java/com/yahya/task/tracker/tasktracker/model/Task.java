package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String issue;
    private String description;
    private LocalDateTime addedDate = LocalDateTime.now();
    private LocalDate dueDate;
    private boolean closed = false;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "task")
    @JsonIgnore @ToString.Exclude @Builder.Default
    private Set<TaskPerson> taskPeople = new HashSet<>();

    private void addPerson(Person person, boolean leader) {
        TaskPerson taskPerson = new TaskPerson(this, person, leader);
        taskPeople.add(taskPerson);
    }

    public void addAssignee(Person person) {
        addPerson(person, false);
    }

    public void addLeader(Person person) {
        addPerson(person, true);
    }
}
