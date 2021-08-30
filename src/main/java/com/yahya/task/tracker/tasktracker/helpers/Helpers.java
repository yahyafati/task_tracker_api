package com.yahya.task.tracker.tasktracker.helpers;

import com.yahya.task.tracker.tasktracker.model.Task;

public class Helpers {

    public static boolean isUserValidForTask(Task task, String username) {
        return task.getOwner().getUsername().equalsIgnoreCase(username) ||
                task.getAssignees().stream().anyMatch(taskPerson ->
                        taskPerson.getProfile().getUsername().equalsIgnoreCase(username));
    }
}
