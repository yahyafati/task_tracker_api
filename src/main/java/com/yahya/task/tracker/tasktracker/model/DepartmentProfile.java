package com.yahya.task.tracker.tasktracker.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@NoArgsConstructor
public class DepartmentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToOne
    private Department department;
    @OneToOne
    private Profile profile;

    public DepartmentProfile(String title, Department department, Profile profile) {
        this.title = title;
        this.department = department;
        this.profile = profile;
    }
}
