package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController implements BasicRestControllerSkeleton<Task> {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    @GetMapping("")
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Task get(@PathVariable Integer id) {
        return taskService.findById(id);
    }

    @Override
    @PostMapping("")
    public Task add(@RequestBody Task item) {
        item.setId(0);
        return taskService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Task update(@PathVariable Integer id, @RequestBody Task updatedItem) {
        updatedItem.setId(id);
        return taskService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        taskService.deleteById(id);
    }
}
