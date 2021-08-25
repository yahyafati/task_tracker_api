package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Track;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/track")
public class TrackController implements BasicRestControllerSkeleton<Track> {

    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    @GetMapping("")
    public List<Track> getAll() {
        return trackService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Track get(@PathVariable Integer id) {
        return trackService.findById(id);
    }

    @Override
    @PostMapping("")
    public Track add(@RequestBody Track item) {
        item.setId(0);
        return trackService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Track update(@PathVariable Integer id, @RequestBody Track updatedItem) {
        updatedItem.setId(id);
        return trackService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        trackService.deleteById(id);
    }
}
