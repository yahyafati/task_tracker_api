package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.TaskPerson;
import com.yahya.task.tracker.tasktracker.service.TaskPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskperson")
@CrossOrigin("*")
public class TaskPersonController implements BasicRestControllerSkeleton<TaskPerson>{

    private final TaskPersonService taskPersonService;

    @Autowired
    public TaskPersonController(TaskPersonService taskPersonService) {
        this.taskPersonService = taskPersonService;
    }

    @Override
    @GetMapping("")
    public List<TaskPerson> getAll() {
        return taskPersonService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public TaskPerson get(@PathVariable Integer id) {
        return taskPersonService.findById(id);
    }

    @Override
    @PostMapping("")
    public TaskPerson add(@RequestBody TaskPerson item) {
        item.setId(0);
        return taskPersonService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public TaskPerson update(@PathVariable Integer id, @RequestBody TaskPerson updatedItem) {
        updatedItem.setId(id);
        return taskPersonService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        taskPersonService.deleteById(id);
    }
}
