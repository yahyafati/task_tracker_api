package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.UserDao;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import com.yahya.task.tracker.tasktracker.model.security.Role;
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
        return findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findUserByUsername(username).orElseThrow();
    }

    @Override
    public User saveUserMeta(UserMeta userMeta) {
        User user = new User(userMeta);
        return save(user);
    }

    @Override
    public UserMeta updateUserMeta(UserMeta userMeta) {
        User oldUser = findByUsername(userMeta.getUsername());
        User updatedUser = new User(userMeta);
        updatedUser.setId(oldUser.getId());
        return new UserMeta(save(updatedUser));
    }

    @Override
    public UserMeta findUserMetaByUsername(String username) {
        return new UserMeta(findByUsername(username));
    }

    @Override
    public boolean setActiveStatus(String username, boolean status) {
        User user = findByUsername(username);
        if (status) {
            user.activate();
        } else {
            user.setEnabled(false);
        }
        return save(user).isActive();
    }

    @Override
    public boolean activateUser(String username) {
        return setActiveStatus(username, true);
    }

    @Override
    public boolean deactivateUser(String username) {
        return setActiveStatus(username, false);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public Role changeRole(String username, String roleName) {
        Role role = roleService.findByName(roleName);
        User user = findByUsername(username);
        user.setRole(role);
        return save(user).getRole();
    }

    @Override
    public boolean isPasswordValid(String username, String password) {
        User user = findByUsername(username);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void changePassword(String username, String password) {
        User user = findByUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
    }

    @Override
    public void resetPassword(String username) {
        changePassword(username, "passwordReset" + username.toLowerCase());
    }
}
