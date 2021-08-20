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
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profilePhotoURL;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "userProfile", fetch = FetchType.EAGER)
    @JsonIgnore @ToString.Exclude @Builder.Default
    private Set<TaskPerson> taskPeople = new HashSet<>();

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
