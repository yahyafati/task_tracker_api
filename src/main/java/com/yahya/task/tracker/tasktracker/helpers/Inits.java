package com.yahya.task.tracker.tasktracker.helpers;


import com.yahya.task.tracker.tasktracker.model.*;
import com.yahya.task.tracker.tasktracker.model.security.Authority;
import com.yahya.task.tracker.tasktracker.model.security.Role;
import com.yahya.task.tracker.tasktracker.security.Permission;
import com.yahya.task.tracker.tasktracker.security.UserRole;
import com.yahya.task.tracker.tasktracker.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Component
public class Inits {

    @Bean
    public CommandLineRunner initializeUsers(UserService userService, PasswordEncoder passwordEncoder,
                                             AuthorityService authorityService, RoleService roleService) {
        return args -> {
            if (authorityService.findAll().size() > 0) {
                return;
            }

            Arrays.stream(Permission.values())
                    .map(Authority::new)
                    .forEach(authorityService::save);

            Arrays.stream(UserRole.values())
                    .forEach(userRole -> {
                        Role role = new Role(userRole.name());
                        userRole.getPermissions().forEach(permission -> {
                            Authority authority = new Authority(permission);
                            role.addAuthority(authority);
                        });
                        roleService.save(role);
                    });

//            User yahya = User.builder()
//                    .username("yahya")
//                    .password(passwordEncoder.encode("12345678"))
//                    .build();
//            yahya.activate();
//            userService.save(yahya);
//
//            User munir = User.builder()
//                    .username("munir")
//                    .password(passwordEncoder.encode("12345678"))
//                    .build();
//            munir.activate();
//            userService.save(munir);
        };
    }

//    @Bean
    public CommandLineRunner initializeDatabase(TaskService taskService, UserProfileService userProfileService, TrackService trackService) {
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

            UserProfile hanan = UserProfile.builder()
                    .firstName("Hanan")
                    .lastName("Fati")
                    .build();

            UserProfile mame = UserProfile.builder()
                    .firstName("Mame")
                    .lastName("Fati")
                    .build();

            UserProfile ilham = UserProfile.builder()
                    .firstName("Ilham")
                    .lastName("Fati")
                    .build();

            UserProfile munir = UserProfile.builder()
                    .firstName("Munir")
                    .build();

            UserProfile salim = UserProfile.builder()
                    .firstName("Salim")
                    .build();
            UserProfile abuke = UserProfile.builder()
                    .firstName("Abuke")
                    .build();
            UserProfile zakiya = UserProfile.builder()
                    .firstName("Zako")
                    .build();

            userProfileService.save(hanan);
            userProfileService.save(mame);
            userProfileService.save(ilham);
            userProfileService.save(munir);
            userProfileService.save(salim);
            userProfileService.save(abuke);
            userProfileService.save(zakiya);


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
