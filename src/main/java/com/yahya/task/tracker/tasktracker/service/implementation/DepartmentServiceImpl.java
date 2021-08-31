package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.DepartmentDao;
import com.yahya.task.tracker.tasktracker.model.Department;
import com.yahya.task.tracker.tasktracker.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department findById(int id) {
        return departmentDao.findById(id).orElseThrow();
    }

    @Override
    public Department save(Department item) {
        return departmentDao.save(item);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        departmentDao.deleteById(id);
        return false;
    }
}
