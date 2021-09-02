package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    private User owner;

    @OneToMany(orphanRemoval = true, mappedBy = "task", fetch = FetchType.EAGER)
    @ToString.Exclude
    @Builder.Default
    private Set<TaskPerson> assignees = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "task", fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    @Builder.Default
    private Set<Track> tracks = new HashSet<>();

    private void addAssignee(Profile profile, boolean leader) {
        if (assignees.stream().anyMatch(taskPerson1 -> taskPerson1.getProfile().getId() == profile.getId()))
            return;
        TaskPerson taskPerson = new TaskPerson(this, profile, leader);
        assignees.add(taskPerson);
    }

    public void addAssignee(TaskPerson taskPerson) {
        if (assignees.stream().anyMatch(taskPerson1 -> taskPerson1.getProfile().getId() == taskPerson.getProfile().getId()))
            return;
        taskPerson.setTask(this);
        assignees.add(taskPerson);
    }

    public void addAssignee(Profile profile) {
        addAssignee(profile, false);
    }

    public void addLeader(Profile profile) {
        addAssignee(profile, true);
    }

    public void addTrack(Track track) {
        track.setTask(this);
        tracks.add(track);
    }

}
