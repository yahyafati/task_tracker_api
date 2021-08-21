package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.security.Role;

public interface RoleService extends BasicServiceSkeleton<Role>{

    Role findByName(String name);
}
