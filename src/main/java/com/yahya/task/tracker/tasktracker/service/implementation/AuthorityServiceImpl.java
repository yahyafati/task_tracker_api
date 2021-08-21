package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.AuthorityDao;
import com.yahya.task.tracker.tasktracker.model.security.Authority;
import com.yahya.task.tracker.tasktracker.service.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityDao authorityDao;

    public AuthorityServiceImpl(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    @Override
    public Authority findById(int id) {
        return authorityDao.findById(id).orElseThrow();
    }

    @Override
    public Authority save(Authority item) {
        item.setName(item.getName().toUpperCase());
        return authorityDao.save(item);
    }

    @Override
    public List<Authority> findAll() {
        return authorityDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        authorityDao.deleteById(id);
        return false;
    }

    @Override
    public Authority findByName(String name) {
        Optional<Authority> authority = authorityDao.findByName(name.toUpperCase());
        return authority.orElse(null);
    }

    @Override
    public void deleteByName(String name) {
        authorityDao.deleteByName(name);
    }
}
