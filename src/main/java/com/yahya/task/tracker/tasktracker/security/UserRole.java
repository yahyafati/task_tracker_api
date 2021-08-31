package com.yahya.task.tracker.tasktracker.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.yahya.task.tracker.tasktracker.security.Permission.*;

public enum UserRole {

    USER(new HashSet<>(
                Arrays.asList(TASK_READ, TRACK_READ, TASK_WRITE, TRACK_WRITE)
            )
        ),
    ADMIN(new HashSet<>(
            Arrays.stream(Permission.values()).collect(Collectors.toSet())
    )),
    SUPER_ADMIN(new HashSet<>());

    private final Set<Permission> permissions;

    UserRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
