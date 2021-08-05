package com.yahya.task.tracker.tasktracker.service.implementation;

import com.yahya.task.tracker.tasktracker.dao.TrackDao;
import com.yahya.task.tracker.tasktracker.model.Track;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackDao trackDao;

    @Autowired
    public TrackServiceImpl(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    @Override
    public Track findById(int id) {
        return trackDao.findById(id).orElseThrow();
    }

    @Override
    public Track save(Track item) {
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
