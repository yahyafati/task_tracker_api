package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.PriorityDao;
import com.yahya.task.tracker.tasktracker.model.Priority;
import com.yahya.task.tracker.tasktracker.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityDao priorityDao;

    @Autowired
    public PriorityServiceImpl(PriorityDao priorityDao) {
        this.priorityDao = priorityDao;
    }


    @Override
    public Priority findById(int id) {
        return priorityDao.findById(id).orElseThrow();
    }

    @Override
    public Priority save(Priority item) {
        return priorityDao.save(item);
    }

    @Override
    public List<Priority> findAll() {
        return priorityDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        priorityDao.deleteById(id);
        return true;
    }
}
