package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {
}
