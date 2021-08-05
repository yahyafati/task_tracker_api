package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.PersonDao;
import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
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
        personDao.deleteById(id);
        return true;
    }
}
