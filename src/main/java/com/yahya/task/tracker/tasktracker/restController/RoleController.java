package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.security.Role;
import com.yahya.task.tracker.tasktracker.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController implements BasicRestControllerSkeleton<Role>{

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    @GetMapping("")
    public List<Role> getAll() {
        return roleService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Role get(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    @Override
    @PostMapping("")
    public Role add(@RequestBody Role item) {
        item.setId(0);
        return roleService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id,
                       @RequestBody Role updatedItem) {
        updatedItem.setId(id);
        return roleService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        roleService.deleteById(id);
    }
}
