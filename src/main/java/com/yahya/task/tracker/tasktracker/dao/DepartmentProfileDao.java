package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.DepartmentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentProfileDao extends JpaRepository<DepartmentProfile, Integer> {
}
