package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.UserProfile;
import com.yahya.task.tracker.tasktracker.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController implements BasicRestControllerSkeleton<UserProfile> {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @Override
    @GetMapping("")
    public List<UserProfile> getAll() {
        return userProfileService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public UserProfile get(@PathVariable Integer id) {
        return userProfileService.findById(id);
    }

    @Override
    @PostMapping("")
    public UserProfile add(@RequestBody UserProfile item) {
        item.setId(0);
        return userProfileService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public UserProfile update(@PathVariable Integer id, @RequestBody UserProfile updatedItem) {
        updatedItem.setId(id);
        return userProfileService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userProfileService.deleteById(id);
    }

//    Custom Controllers
//
//    @GetMapping("/{id}/tasks")
//    public Set<TaskPerson> getPersonsTasks(@PathVariable Integer id) {
//        return personService.findTaskByPersonId(id);
//    }



}
