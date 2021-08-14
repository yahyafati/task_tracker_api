package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Builder.Default
    private String email = "";


//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
//            mappedBy = "person", fetch = FetchType.EAGER)
    @OneToMany(orphanRemoval = true, mappedBy = "person", fetch = FetchType.EAGER)
    @JsonIgnore @ToString.Exclude @Builder.Default
    private Set<TaskPerson> taskPeople = new HashSet<>();

}
