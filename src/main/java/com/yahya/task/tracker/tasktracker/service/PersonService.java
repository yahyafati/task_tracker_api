package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;

import java.util.Set;

public interface PersonService extends BasicServiceSkeleton<Person> {

    Set<TaskPerson> findTaskByPerson(Person person);
    Set<TaskPerson> findTaskByPersonId(int id);
}
