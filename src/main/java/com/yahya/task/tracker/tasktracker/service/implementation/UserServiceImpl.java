package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.UserDao;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import com.yahya.task.tracker.tasktracker.service.RoleService;
import com.yahya.task.tracker.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public User save(User item) {
        if (item.getId() == 0) {
            if (item.getRole() == null) {
                item.setRole(roleService.findByName("USER"));
            }
            final String encodedPassword = passwordEncoder.encode(item.getPassword());
            item.setPassword(encodedPassword);
        } else {
            User oldUser = findById(item.getId());
            item.setPassword(oldUser.getPassword());
        }
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

    @Override
    public User saveUserMeta(UserMeta userMeta) {
        User user = new User(userMeta);
        return save(user);
    }
}