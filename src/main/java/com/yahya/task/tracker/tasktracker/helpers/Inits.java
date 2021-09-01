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
    public CommandLineRunner initializeRoles(UserService userService, PasswordEncoder passwordEncoder,
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
        };
    }

    @Bean
    public CommandLineRunner initializeSuperAdmin(UserService userService,
                                                  RoleService roleService) {
//        TODO Change this to value in properties file instead of hardcoding it

//        TODO Change this ugly code to more fitting code. Return null on non existence from all services
        return args -> {
            try {
                userService.findByUsername("superadmin");
            } catch (Exception e) {
                System.err.println("No Super-Admin Found. Creating a new one.");
                User superAdmin = User.builder()
                        .username("superadmin")
                        .password("superadmin")
                        .role(roleService.findByName("SUPER_ADMIN"))
                        .build();
                superAdmin.activate();
                userService.save(superAdmin);
            }
        };
    }

//    @Bean
    public CommandLineRunner initializeDatabase(TaskService taskService, ProfileService profileService, TrackService trackService) {
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

            Profile hanan = Profile.builder()
                    .firstName("Hanan")
                    .lastName("Fati")
                    .build();

            Profile mame = Profile.builder()
                    .firstName("Mame")
                    .lastName("Fati")
                    .build();

            Profile ilham = Profile.builder()
                    .firstName("Ilham")
                    .lastName("Fati")
                    .build();

            Profile munir = Profile.builder()
                    .firstName("Munir")
                    .build();

            Profile salim = Profile.builder()
                    .firstName("Salim")
                    .build();
            Profile abuke = Profile.builder()
                    .firstName("Abuke")
                    .build();
            Profile zakiya = Profile.builder()
                    .firstName("Zako")
                    .build();

            profileService.save(hanan);
            profileService.save(mame);
            profileService.save(ilham);
            profileService.save(munir);
            profileService.save(salim);
            profileService.save(abuke);
            profileService.save(zakiya);


            hananTask.addLeader(hanan);
            mameTask.addLeader(mame);
            ilhamTask.addLeader(ilham);

            allTask.addLeader(hanan);
            allTask.addAssignee(mame);
            allTask.addAssignee(ilham);

//            taskService.saveNew(hananTask);
//            taskService.saveNew(ilhamTask);
//            taskService.saveNew(mameTask);
//            taskService.saveNew(allTask);

            System.out.println("\n\nInitiated\n\n");
        };
    }

}
