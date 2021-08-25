package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TaskPersonDao;
import com.yahya.task.tracker.tasktracker.dao.UserProfileDao;
import com.yahya.task.tracker.tasktracker.model.UserProfile;
import com.yahya.task.tracker.tasktracker.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileDao userProfileDao;
    private final TaskPersonDao taskPersonDao;

    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao, TaskPersonDao taskPersonDao) {
        this.userProfileDao = userProfileDao;
        this.taskPersonDao = taskPersonDao;
    }

    @Override
    public UserProfile findById(int id) {
        return userProfileDao.findById(id).orElseThrow();
    }

    @Override
    public UserProfile save(UserProfile item) {
        return userProfileDao.save(item);
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
//        Person person = findById(id);
//        person.getTaskPeople().forEach(taskPerson -> {
//            System.out.println("\n\n" + taskPerson + "\n\n");
//            taskPersonDao.delete(taskPerson);
//        });
//        List<TaskPerson> copyTaskPeople = taskPersonDao.findAllByPersonId(id);
        taskPersonDao.deleteAllByUserProfileId(id);
//        copyTaskPeople.forEach(taskPerson -> {
////            taskPerson.setPerson(null);
//            taskPersonDao.deleteById(taskPerson.getId());
//        });
        userProfileDao.deleteById(id);
        return true;
    }

//    @Override
//    public Set<TaskPerson> findTaskByPerson(Person person) {
//        return person.getTaskPeople();
//    }
//
//    @Override
//    public Set<TaskPerson> findTaskByPersonId(int personId) {
//        return findTaskByPerson(findById(personId));
//    }
}
