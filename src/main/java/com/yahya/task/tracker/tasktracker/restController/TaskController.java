package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.model.Track;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import static com.yahya.task.tracker.tasktracker.helpers.Helpers.isUserValidForTask;

@RestController
@RequestMapping("/api/task")
public class TaskController implements BasicRestControllerSkeleton<Task> {

    private final TaskService taskService;
    private final TrackService trackService;

    @Autowired
    public TaskController(TaskService taskService, TrackService trackService) {
        this.taskService = taskService;
        this.trackService = trackService;
    }

    @GetMapping(value = "/exists/{id}")
    public boolean exists(@PathVariable Integer id) {
        return taskService.exists(id);
    }

    @GetMapping("")
    public List<Task> getAll() {
        return taskService.findAllForCurrentUser();
    }

    @Override
    @Deprecated
    public Task get(Integer id) {
        return null;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Integer id, Principal principal, HttpServletResponse response) {
        Task task = taskService.findById(id);
        if (!isUserValidForTask(task, principal.getName())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new RuntimeException(new AccessDeniedException("No task for you with this id"));
        }
        return task;
    }

    @Override
    @PostMapping("")
    public Task add(@RequestBody Task item) {
        item.setId(0);
        return taskService.saveNew(item);
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

//    Custom Controllers

//    TODO To be deleted after
    //    Task Person
//    @GetMapping("/{id}/persons")
//    public Set<TaskPerson> getAssignees(@PathVariable Integer id) {
//        return taskService.findPersonByTaskId(id);
//    }
//
//    @PostMapping("/{id}/persons")
//    public TaskPerson getAssignees(@PathVariable Integer id, @RequestBody TaskPerson taskPerson) {
//        return taskService.saveTaskPerson(id, taskPerson);
//    }
//
//    @PutMapping("/{task_id}/persons/{taskPerson_id}")
//    public TaskPerson updateAssignee(@PathVariable Integer task_id,
//                                     @PathVariable Integer taskPerson_id,
//                                     @RequestBody TaskPerson updatedTaskPerson) {
//        updatedTaskPerson.setId(taskPerson_id);
//        return taskService.saveTaskPerson(task_id, updatedTaskPerson);
//    }
//
//    @DeleteMapping("/{task_id}/persons/{taskPerson_id}")
//    public void removeAssignee(@PathVariable Integer task_id, @PathVariable Integer taskPerson_id) {
//        taskService.deleteTaskPersonById(taskPerson_id);
//    }


    //    Tracks Controller
    @GetMapping("/{id}/tracks")
    public Set<Track> getTracks(@PathVariable Integer id, Principal principal, HttpServletResponse response) {
        Task task = taskService.findById(id);
        if (!isUserValidForTask(task, principal.getName())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new RuntimeException(new AccessDeniedException("No task for you with this id"));
        }
        return taskService.findTracksByTask(task);
    }

    @PostMapping("/{id}/tracks")
    public Track addTrack(@PathVariable Integer id, @RequestBody Track track, Principal principal, HttpServletResponse response) {
        Task task = taskService.findById(id);
        if (!isUserValidForTask(task, principal.getName())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new RuntimeException(new AccessDeniedException("No task for you with this id"));
        }
        track.setTask(task);
        return trackService.save(track);
    }

    @DeleteMapping("/{task_id}/tracks/{track_id}")
    public void deleteTask(@PathVariable Integer task_id, @PathVariable Integer track_id, Principal principal, HttpServletResponse response) {
//        TODO Must check if the track is the users
        Task task = taskService.findById(task_id);
        if (!isUserValidForTask(task, principal.getName())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            throw new RuntimeException(new AccessDeniedException("No task for you with this id"));
        }
        trackService.deleteById(track_id);
    }
}
