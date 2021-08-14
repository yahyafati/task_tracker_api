package com.yahya.task.tracker.tasktracker;

import com.yahya.task.tracker.tasktracker.model.Person;
import com.yahya.task.tracker.tasktracker.model.Priority;
import com.yahya.task.tracker.tasktracker.model.Status;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.service.PersonService;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootApplication
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initializeDatabase(TaskService taskService, PersonService personService, TrackService trackService) {
        return args -> {
            if (taskService.findAll().size() > 0) return;

            Task hananTask = Task.builder()
                    .issue("Hanan Passport Appointment")
                    .description("This is Hanan's Passport Appointment.")
                    .dueDate(LocalDate.of(2021, Month.AUGUST, 11))
                    .priority(Priority.HIGH)
                    .build();
            Task mameTask = Task.builder()
                    .issue("Mame Passport Appointment")
                    .description("This is Mame's Passport Appointment.")
                    .dueDate(LocalDate.of(2021, Month.AUGUST, 21))
                    .priority(Priority.MEDIUM)
                    .status(Status.DONE)
                    .build();
            Task ilhamTask = Task.builder()
                    .issue("Ilham Passport Appointment")
                    .description("This is Ilham's Passport Appointment.")
                    .dueDate(LocalDate.of(2021, Month.AUGUST, 31))
                    .priority(Priority.LOW)
                    .status(Status.CANCELLED)
                    .build();

            Task allTask = Task.builder()
                    .issue("Household Chores")
                    .description("Doing dishes, and cleaning the living room")
                    .dueDate(LocalDate.of(2021, Month.SEPTEMBER, 12))
                    .priority(Priority.HIGH)
                    .build();

            Person hanan = Person.builder()
                    .name("Hanan Fati")
                    .build();

            Person mame = Person.builder()
                    .name("Mame Fati")
                    .build();

            Person ilham = Person.builder()
                    .name("Ilham Fati")
                    .build();

            Person munir = Person.builder()
                    .name("Munir Fati")
                    .build();

            Person salim = Person.builder()
                    .name("Salim")
                    .build();
            Person abuke = Person.builder()
                    .name("Abuke")
                    .build();
            Person zakiya = Person.builder()
                    .name("Zako")
                    .build();

            personService.save(hanan);
            personService.save(mame);
            personService.save(ilham);
            personService.save(munir);
            personService.save(salim);
            personService.save(abuke);
            personService.save(zakiya);


            hananTask.addLeader(hanan);
            mameTask.addLeader(mame);
            ilhamTask.addLeader(ilham);

            allTask.addLeader(hanan);
            allTask.addAssignee(mame);
            allTask.addAssignee(ilham);

            taskService.saveNew(hananTask);
            taskService.saveNew(ilhamTask);
            taskService.saveNew(mameTask);
            taskService.saveNew(allTask);

            System.out.println("\n\nInitiated\n\n");
        };
    }
}
