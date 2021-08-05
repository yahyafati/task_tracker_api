package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Integer> {
}
