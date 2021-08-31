package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.DepartmentProfileDao;
import com.yahya.task.tracker.tasktracker.model.DepartmentProfile;
import com.yahya.task.tracker.tasktracker.service.DepartmentProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentProfileServiceImpl implements DepartmentProfileService {

    private final DepartmentProfileDao departmentProfileDao;

    public DepartmentProfileServiceImpl(DepartmentProfileDao departmentProfileDao) {
        this.departmentProfileDao = departmentProfileDao;
    }

    @Override
    public DepartmentProfile findById(int id) {
        return departmentProfileDao.findById(id).orElseThrow();
    }

    @Override
    public DepartmentProfile save(DepartmentProfile item) {
        return departmentProfileDao.save(item);
    }

    @Override
    public List<DepartmentProfile> findAll() {
        return departmentProfileDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        departmentProfileDao.deleteById(id);
        return false;
    }
}
