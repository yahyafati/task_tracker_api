package com.yahya.task.tracker.tasktracker.restController;

import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/person")
public class PersonController implements BasicRestControllerSkeleton<Person> {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    @GetMapping("")
    public List<Person> getAll() {
        return personService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Person get(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @Override
    @PostMapping("")
    public Person add(@RequestBody Person item) {
        item.setId(0);
        return personService.save(item);
    }

    @Override
    @PutMapping("/{id}")
    public Person update(@PathVariable Integer id, @RequestBody Person updatedItem) {
        updatedItem.setId(id);
        return personService.save(updatedItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        personService.deleteById(id);
    }

//    Custom Controllers

    @GetMapping("/{id}/tasks")
    public Set<Task> getPersonsTasks(@PathVariable Integer id) {
        return personService.findTaskByPersonId(id);
    }



}
