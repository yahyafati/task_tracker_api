package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import com.yahya.task.tracker.tasktracker.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController implements BasicRestControllerSkeleton<User>{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("")
    public List<User> getAll() {
        return userService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @Override
    @Deprecated
    public User add(User item) { return userService.save(item); }

    @PostMapping("")
    public User add(@RequestBody UserMeta userMeta) {
        return userService.saveUserMeta(userMeta);
    }

    @Override
    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User updatedItem) {
        updatedItem.setId(id);
        return userService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}
