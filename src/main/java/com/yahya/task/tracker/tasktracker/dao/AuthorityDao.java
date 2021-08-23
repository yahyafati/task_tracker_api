package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {

    Optional<Authority> findByAuthority(String authority);

    void deleteByAuthority(String authority);
}
