package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.model.Task;

import java.util.Set;

public interface TaskService extends BasicServiceSkeleton<Task> {

    Set<Person> findPersonByTask(Task task);
    Set<Person> findPersonByTaskId(int taskId);
}
