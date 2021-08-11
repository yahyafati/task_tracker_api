package com.yahya.task.tracker.tasktracker.model;

public enum Status {
    ACTIVE("Active"),
    DONE("Finished"),
    CANCELLED("Cancelled");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
