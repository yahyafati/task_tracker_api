package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.model.Task;

import java.util.Set;

public interface PersonService extends BasicServiceSkeleton<Person> {

    Set<Task> findTaskByPerson(Person person);
    Set<Task> findTaskByPersonId(int id);
}
