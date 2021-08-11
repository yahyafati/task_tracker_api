package com.yahya.task.tracker.tasktracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime addedTime = LocalDateTime.now();
    private String title;
    private String description;
    private LocalDate date;
    @ManyToOne
    private Task task;

    public Track(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Track(String title, String description, LocalDate date, Task task) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.task = task;
    }
}
