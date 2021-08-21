package com.yahya.task.tracker.tasktracker.service;

import com.yahya.task.tracker.tasktracker.model.security.Authority;

public interface AuthorityService extends BasicServiceSkeleton<Authority>{

    Authority findByName(String name);
    void deleteByName(String name);
}
