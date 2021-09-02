package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.ProfileDao;
import com.yahya.task.tracker.tasktracker.dao.TaskPersonDao;
import com.yahya.task.tracker.tasktracker.model.Profile;
import com.yahya.task.tracker.tasktracker.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final TaskPersonDao taskPersonDao;

    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao, TaskPersonDao taskPersonDao) {
        this.profileDao = profileDao;
        this.taskPersonDao = taskPersonDao;
    }

    @Override
    public Profile findById(int id) {
        return profileDao.findById(id).orElseThrow();
    }

    @Override
    public Profile save(Profile item) {
        return profileDao.save(item);
    }

    @Override
    public List<Profile> findAll() {
        return profileDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
//        Person person = findById(id);
//        person.getTaskPeople().forEach(taskPerson -> {
//            System.out.println("\n\n" + taskPerson + "\n\n");
//            taskPersonDao.delete(taskPerson);
//        });
//        List<TaskPerson> copyTaskPeople = taskPersonDao.findAllByPersonId(id);
        taskPersonDao.deleteAllByProfileId(id);
//        copyTaskPeople.forEach(taskPerson -> {
////            taskPerson.setPerson(null);
//            taskPersonDao.deleteById(taskPerson.getId());
//        });
        profileDao.deleteById(id);
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
