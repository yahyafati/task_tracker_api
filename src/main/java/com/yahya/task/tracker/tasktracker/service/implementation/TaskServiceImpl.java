package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TaskDao;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task findById(int id) {
        return taskDao.findById(id).orElseThrow();
    }

    @Override
    public Task save(Task item) {
        return taskDao.save(item);
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
        return task.getTaskPeople();
    }

    @Override
    public Set<TaskPerson> findPersonByTaskId(int taskId) {
        return findPersonByTask(findById(taskId));
    }
}
