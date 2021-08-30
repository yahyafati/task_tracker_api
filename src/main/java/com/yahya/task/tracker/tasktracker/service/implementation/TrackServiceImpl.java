package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TrackDao;
import com.yahya.task.tracker.tasktracker.model.Track;
import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import com.yahya.task.tracker.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackDao trackDao;
    private final UserService userService;

    @Autowired
    public TrackServiceImpl(TrackDao trackDao, UserService userService) {
        this.trackDao = trackDao;
        this.userService = userService;
    }

    @Override
    public Track findById(int id) {
        return trackDao.findById(id).orElseThrow();
    }

    @Override
    public Track save(Track item) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByUsername(principal.getName());
        item.setAddedTime(LocalDateTime.now());
        item.setOwner(currentUser);
        return trackDao.save(item);
    }

    @Override
    public List<Track> findAll() {
        return trackDao.findAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        trackDao.deleteById(id);
        return true;
    }
}
