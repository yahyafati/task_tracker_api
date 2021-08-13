package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @Builder.Default
    private LocalDateTime addedDate = LocalDateTime.now();
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING) @Builder.Default
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "task", fetch = FetchType.EAGER)
    @ToString.Exclude @Builder.Default
    private Set<TaskPerson> assignees = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "task", fetch = FetchType.EAGER)
    @JsonIgnore @ToString.Exclude @Builder.Default
    private Set<Track> tracks = new HashSet<>();

    private void addAssignee(Person person, boolean leader) {
        if (assignees.stream().anyMatch(taskPerson1 -> taskPerson1.getPerson().getId() == person.getId()))
            return;
        TaskPerson taskPerson = new TaskPerson(this, person, leader);
        assignees.add(taskPerson);
    }

    public void addAssignee(TaskPerson taskPerson) {
        if (assignees.stream().anyMatch(taskPerson1 -> taskPerson1.getPerson().getId() == taskPerson.getPerson().getId()))
            return;
        taskPerson.setTask(this);
        assignees.add(taskPerson);
    }

    public void addAssignee(Person person) {
        addAssignee(person, false);
    }

    public void addLeader(Person person) {
        addAssignee(person, true);
    }

    public void addTrack(Track track) {
        track.setTask(this);
        tracks.add(track);
    }

}
