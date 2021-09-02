package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TaskPersonDao;
import com.yahya.task.tracker.tasktracker.model.Profile;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import com.yahya.task.tracker.tasktracker.service.ProfileService;
import com.yahya.task.tracker.tasktracker.service.TaskPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskPersonServiceImpl implements TaskPersonService {

    private final TaskPersonDao taskPersonDao;
    private final ProfileService profileService;

    @Autowired
    public TaskPersonServiceImpl(TaskPersonDao taskPersonDao, ProfileService profileService) {
        this.taskPersonDao = taskPersonDao;
        this.profileService = profileService;
    }

    @Override
    public TaskPerson findById(int id) {
        return taskPersonDao.findById(id).orElseThrow();
    }

    @Override
    public TaskPerson save(TaskPerson item) {
        Profile profile = item.getProfile();
        item.setProfile(profileService.findById(profile.getId()));
        return taskPersonDao.save(item);
    }

    @Override
    public List<TaskPerson> findAll() {
        return taskPersonDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        taskPersonDao.deleteById(id);
        return true;
    }

    @Override
    public void deleteAllByTaskId(int taskId) {
        taskPersonDao.deleteAllByTaskId(taskId);
    }
}
