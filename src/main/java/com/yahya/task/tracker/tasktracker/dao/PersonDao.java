package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Integer> {
}
