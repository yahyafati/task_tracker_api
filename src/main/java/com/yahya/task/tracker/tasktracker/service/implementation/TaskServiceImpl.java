package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TaskDao;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import com.yahya.task.tracker.tasktracker.model.Track;
import com.yahya.task.tracker.tasktracker.service.TaskPersonService;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;
    private final TaskPersonService taskPersonService;
    private final TrackService trackService;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao, TaskPersonService taskPersonService, TrackService trackService) {
        this.taskDao = taskDao;
        this.taskPersonService = taskPersonService;
        this.trackService = trackService;
    }

    @Override
    public Task findById(int id) {
        return taskDao.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Task save(Task item) {
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
        Task savedItem = save(item);
        Track initialTrack = new Track(
                "Task Created", "Task has been created",
                LocalDate.now(), savedItem);
        trackService.save(initialTrack);

        return savedItem;
    }

    @Override
    public TaskPerson saveTaskPerson(int taskId, TaskPerson taskPerson) {
        return null;
    }

    @Override
    public void deleteTaskPersonById(int taskPersonId) {

    }

    @Override
    public boolean exists(Integer id) {
        return taskDao.existsById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        taskDao.deleteById(id);
        return true;
    }

    @Override
    public Set<TaskPerson> findPersonByTask(Task task) {
        return task.getAssignees();
    }

    @Override
    public Set<TaskPerson> findPersonByTaskId(int taskId) {
        return findPersonByTask(findById(taskId));
    }

    @Override
    public Set<Track> findTracksByTask(Task task) {
        return task.getTracks();
    }

    @Override
    public Set<Track> findTracksByTaskId(int taskId) {
        return findTracksByTask(findById(taskId));
    }


}
