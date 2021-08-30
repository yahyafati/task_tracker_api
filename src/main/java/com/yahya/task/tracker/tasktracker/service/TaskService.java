package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import com.yahya.task.tracker.tasktracker.model.Track;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface TaskService extends BasicServiceSkeleton<Task> {


    List<Task> findAllForCurrentUser();

//    Set<TaskPerson> findPersonByTask(Task task);
//    Set<TaskPerson> findPersonByTaskId(int taskId);

    Set<Track> findTracksByTask(Task task);
    Set<Track> findTracksByTaskId(int taskId);


    Task saveNew(Task item);

//    TaskPerson saveTaskPerson(int taskId, TaskPerson taskPerson);
//    void deleteTaskPersonById(int taskPersonId);

    boolean exists(Integer id);
}
