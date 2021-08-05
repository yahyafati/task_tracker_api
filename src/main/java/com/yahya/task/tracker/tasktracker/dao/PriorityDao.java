package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityDao extends JpaRepository<Priority, Integer> {
}
