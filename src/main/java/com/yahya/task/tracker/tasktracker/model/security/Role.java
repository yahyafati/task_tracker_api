package com.yahya.task.tracker.tasktracker.model.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }
}
