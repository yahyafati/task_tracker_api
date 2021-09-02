package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BasicServiceSkeleton<User>, UserDetailsService {

    User findByUsername(String username);
    User saveUserMeta(UserMeta userMeta);

    UserMeta findUserMetaByUsername(String username);

    boolean setActiveStatus(String username, boolean status);

    boolean activateUser(String username);

    boolean deactivateUser(String username);
}
