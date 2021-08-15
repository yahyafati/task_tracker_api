package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.PersonDao;
import com.yahya.task.tracker.tasktracker.dao.TaskPersonDao;
import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    private final TaskPersonDao taskPersonDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao, TaskPersonDao taskPersonDao) {
        this.personDao = personDao;
        this.taskPersonDao = taskPersonDao;
    }

    @Override
    public Person findById(int id) {
        return personDao.findById(id).orElseThrow();
    }

    @Override
    public Person save(Person item) {
        return personDao.save(item);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
//        Person person = findById(id);
//        person.getTaskPeople().forEach(taskPerson -> {
//            System.out.println("\n\n" + taskPerson + "\n\n");
//            taskPersonDao.delete(taskPerson);
//        });
//        List<TaskPerson> copyTaskPeople = taskPersonDao.findAllByPersonId(id);
        taskPersonDao.deleteAllByPersonId(id);
//        copyTaskPeople.forEach(taskPerson -> {
////            taskPerson.setPerson(null);
//            taskPersonDao.deleteById(taskPerson.getId());
//        });
        personDao.deleteById(id);
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
