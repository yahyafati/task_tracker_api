package com.yahya.task.tracker.tasktracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profilePhotoURL;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore @ToString.Exclude
    private User user;

    @OneToOne(orphanRemoval = true, mappedBy = "profile")
    private DepartmentProfile departmentProfile;

    @OneToMany(orphanRemoval = true, mappedBy = "profile", fetch = FetchType.EAGER)
    @JsonIgnore @ToString.Exclude @Builder.Default
    private Set<TaskPerson> taskPeople = new HashSet<>();

    public Profile(UserMeta userMeta) {
        this.firstName = userMeta.getFirstName();
        this.lastName = userMeta.getLastName();
        this.email = userMeta.getEmail();
        this.phone = userMeta.getPhone();
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Transient
    public String getUsername() {
        return user.getUsername();
    }

}
