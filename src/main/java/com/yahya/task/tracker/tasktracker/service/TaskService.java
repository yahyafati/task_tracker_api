package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;

import java.util.Set;

public interface TaskService extends BasicServiceSkeleton<Task> {

    Set<TaskPerson> findPersonByTask(Task task);
    Set<TaskPerson> findPersonByTaskId(int taskId);
}
