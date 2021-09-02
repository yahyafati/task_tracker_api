package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TaskDao;
import com.yahya.task.tracker.tasktracker.helpers.Helpers;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.Track;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.service.TaskPersonService;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import com.yahya.task.tracker.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;
    private final TaskPersonService taskPersonService;
    private final TrackService trackService;
    private final UserService userService;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao, TaskPersonService taskPersonService, TrackService trackService, UserService userService) {
        this.taskDao = taskDao;
        this.taskPersonService = taskPersonService;
        this.trackService = trackService;
        this.userService = userService;
    }

    @Override
    public Task findById(int id) {
        return taskDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Task save(Task item) {
        Principal currentPrincipal = SecurityContextHolder.getContext().getAuthentication();
        if (!item.getOwner().getUsername().equalsIgnoreCase(currentPrincipal.getName())) {
            throw new RuntimeException(new AccessDeniedException("You can't update this task. You are not the creator of the task."));
        }
        item.getAssignees().forEach(taskPerson -> taskPerson.setId(0));
        Task savedItem = taskDao.save(item);
        taskPersonService.deleteAllByTaskId(savedItem.getId());

        Task finalSavedItem = savedItem;
        item.getAssignees().forEach(taskPerson -> {
            taskPerson.setTask(finalSavedItem);
            taskPersonService.save(taskPerson);
        });


        savedItem = findById(savedItem.getId());
        return savedItem;
    }

    @Override
    public Task saveNew(Task item) {
        Principal ownerPrincipal = SecurityContextHolder.getContext().getAuthentication();
        String username = ownerPrincipal.getName();
        User owner = userService.findByUsername(username);
        item.setOwner(owner);
        Task savedItem = save(item);
        Track initialTrack = new Track(
                "Task Created", "Task has been created",
                LocalDate.now(), savedItem);
        trackService.save(initialTrack);

        return savedItem;
    }

//    @Override
//    public TaskPerson saveTaskPerson(int taskId, TaskPerson taskPerson) { }

//    @Override
//    public void deleteTaskPersonById(int taskPersonId) { }

    @Override
    public boolean exists(Integer id) {
        Optional<Task> task = taskDao.findById(id);
        if (task.isEmpty()) {
            return false;
        }
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return Helpers.isUserValidForTask(task.get(), principal.getName());
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public List<Task> findAllForCurrentUser() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return findAll()
                .stream()
                .filter(task -> Helpers.isUserValidForTask(task, principal.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Integer id) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Task task = findById(id);
        if (task.getOwner().getUsername().equalsIgnoreCase(principal.getName())) {
            taskDao.deleteById(id);
        } else {
            throw new RuntimeException(new AccessDeniedException(
                    "You can't delete this task. Only the creator (owner) of the task can delete a task."
            ));
        }
        return true;
    }

//    @Override
//    public Set<TaskPerson> findPersonByTask(Task task) {
//        return task.getAssignees();
//    }
//    @Override
//    public Set<TaskPerson> findPersonByTaskId(int taskId) {
//        return findPersonByTask(findById(taskId));
//    }

    @Override
    public Set<Track> findTracksByTask(Task task) {
        return task.getTracks();
    }

    @Override
    public Set<Track> findTracksByTaskId(int taskId) {
        return findTracksByTask(findById(taskId));
    }


}
