package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.UserDao;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public User save(User item) {
        return userDao.save(item);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        userDao.deleteById(id);
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findUserByUsername(username).orElseThrow();
    }
}
