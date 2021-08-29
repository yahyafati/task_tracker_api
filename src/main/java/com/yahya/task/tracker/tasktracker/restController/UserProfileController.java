package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Profile;
import com.yahya.task.tracker.tasktracker.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController implements BasicRestControllerSkeleton<Profile> {

    private final ProfileService profileService;

    @Autowired
    public UserProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    @GetMapping("")
    public List<Profile> getAll() {
        return profileService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Profile get(@PathVariable Integer id) {
        return profileService.findById(id);
    }

    @Override
    @PostMapping("")
    public Profile add(@RequestBody Profile item) {
        item.setId(0);
        return profileService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Profile update(@PathVariable Integer id, @RequestBody Profile updatedItem) {
        updatedItem.setId(id);
        return profileService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        profileService.deleteById(id);
    }

//    Custom Controllers
//
//    @GetMapping("/{id}/tasks")
//    public Set<TaskPerson> getPersonsTasks(@PathVariable Integer id) {
//        return personService.findTaskByPersonId(id);
//    }



}
