package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Department;
import com.yahya.task.tracker.tasktracker.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController implements BasicRestControllerSkeleton<Department> {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    @GetMapping("")
    public List<Department> getAll() {
        return departmentService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Department get(@PathVariable Integer id) {
        return departmentService.findById(id);
    }

    @Override
    @PostMapping("")
    public Department add(@RequestBody Department item) {
        item.setId(0);
        return departmentService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Department update(@PathVariable Integer id, @RequestBody Department updatedItem) {
        updatedItem.setId(id);
        return departmentService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        departmentService.deleteById(id);
    }
}
