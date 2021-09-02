package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.TaskPerson;

public interface TaskPersonService extends BasicServiceSkeleton<TaskPerson> {

    void deleteAllByTaskId(int taskId);
}
