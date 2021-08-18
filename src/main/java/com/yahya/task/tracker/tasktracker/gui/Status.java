package com.yahya.task.tracker.tasktracker.gui;

public enum Status {
    RUNNING("Running"), STARTING("Starting"), SHUTTING("Shutting Down"), CLOSED("Closed");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String displayStatus() {
        return "Server is " + status;
    }

}
