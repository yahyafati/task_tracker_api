package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Priority;
import com.yahya.task.tracker.tasktracker.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priority")
public class PriorityController implements BasicRestControllerSkeleton<Priority> {

    private final PriorityService priorityService;

    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    @GetMapping("")
    public List<Priority> getAll() {
        return priorityService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Priority get(@PathVariable Integer id) {
        return priorityService.findById(id);
    }

    @Override
    @PostMapping("")
    public Priority add(@RequestBody Priority item) {
        item.setId(0);
        return priorityService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Priority update(@PathVariable Integer id, @RequestBody Priority updatedItem) {
        updatedItem.setId(id);
        return priorityService.save(updatedItem);
    }

    @Override
    public void delete(Integer id) {
        priorityService.deleteById(id);
    }
}
