package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
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
    @ManyToOne
    @JsonIgnore
    private User owner;

    public Track(String title, String description, LocalDate date) {
        this(title, description, date, null);
    }

    public Track(String title, String description, LocalDate date, Task task) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.task = task;
    }

    public UserMeta getUserMeta() {
        return new UserMeta(owner);
    }
}
