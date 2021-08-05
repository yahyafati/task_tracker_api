package com.yahya.task.tracker.tasktracker.dao;

import com.yahya.task.tracker.tasktracker.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackDao extends JpaRepository<Track, Integer> {
}
