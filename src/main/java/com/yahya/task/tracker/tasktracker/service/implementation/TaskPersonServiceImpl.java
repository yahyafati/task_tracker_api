package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TaskPersonDao;
import com.yahya.task.tracker.tasktracker.model.UserProfile;
import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import com.yahya.task.tracker.tasktracker.service.UserProfileService;
import com.yahya.task.tracker.tasktracker.service.TaskPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskPersonServiceImpl implements TaskPersonService {

    private final TaskPersonDao taskPersonDao;
    private final UserProfileService userProfileService;

    @Autowired
    public TaskPersonServiceImpl(TaskPersonDao taskPersonDao, UserProfileService userProfileService) {
        this.taskPersonDao = taskPersonDao;
        this.userProfileService = userProfileService;
    }

    @Override
    public TaskPerson findById(int id) {
        return taskPersonDao.findById(id).orElseThrow();
    }

    @Override
    public TaskPerson save(TaskPerson item) {
        UserProfile userProfile = item.getUserProfile();
        item.setUserProfile(userProfileService.findById(userProfile.getId()));
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
