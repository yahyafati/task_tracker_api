package com.yahya.task.tracker.tasktracker.security;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Permission {
    TASK_READ("task:read"),
    TASK_WRITE("task:write"),
    TASK_PERSON_READ("taskPerson:read"),
    TASK_PERSON_WRITE("taskPerson:write"),
    TRACK_READ("track:read"),
    TRACK_WRITE("track:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    USER_PROFILE_READ("userProfile:read"),
    USER_PROFILE_WRITE("userProfile:write")
    ;

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public static Set<String> getPermissionParents() {
        return Arrays.stream(values())
                .map(Permission::getPermission)
//                TODO Add Apache Common Lang for the below to work or add own implementation
//                .map(s -> StringUtils.substringBefore(s, ":"))
                .collect(Collectors.toSet());
    }
}
