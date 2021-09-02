package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.User;
import com.yahya.task.tracker.tasktracker.model.helper.UserMeta;
import com.yahya.task.tracker.tasktracker.model.security.Role;
import com.yahya.task.tracker.tasktracker.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController implements BasicRestControllerSkeleton<User> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("")
    public List<User> getAll() {
        return userService.findAll();
    }


    @GetMapping("/meta/{username}")
    public UserMeta getMeta(@PathVariable String username) {
        return userService.findUserMetaByUsername(username);
    }

    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @Override
    @Deprecated
    public User add(User item) {
        return userService.save(item);
    }

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

    @GetMapping("/getCurrentUser")
    public UserMeta getCurrentUser(Principal principal, HttpServletResponse response) {
        if (StringUtils.isEmpty(principal.getName())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            throw new RuntimeException(new AccessDeniedException("No user token is given in the header to get the user."));
        }
        return userService.findUserMetaByUsername(principal.getName());
    }

    @PutMapping("/resetPassword")
    public void resetPassword(@RequestParam String username) {
        userService.resetPassword(username);
    }

    @PatchMapping("/deactivate/{username}")
    public boolean deactivateUser(@PathVariable String username) {
        return userService.deactivateUser(username);
    }

    @PatchMapping("/activate/{username}")
    public boolean activateUser(@PathVariable String username) {
        return userService.activateUser(username);
    }

    @GetMapping("/exists/{username}")
    public boolean existsByUsername(@PathVariable String username) {
        return userService.existsByUsername(username);
    }

    @PatchMapping(value = "/changeRole/{username}")
    public Role changeRole(@PathVariable String username, @RequestParam String roleName) {
        return userService.changeRole(username, roleName);
    }
}
