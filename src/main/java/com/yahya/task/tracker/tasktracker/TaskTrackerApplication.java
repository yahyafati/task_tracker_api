package com.yahya.task.tracker.tasktracker;

import com.yahya.task.tracker.tasktracker.gui.MainFrame;
import com.yahya.task.tracker.tasktracker.model.UserProfile;
import com.yahya.task.tracker.tasktracker.model.Priority;
import com.yahya.task.tracker.tasktracker.model.Status;
import com.yahya.task.tracker.tasktracker.model.Task;
import com.yahya.task.tracker.tasktracker.service.UserProfileService;
import com.yahya.task.tracker.tasktracker.service.TaskService;
import com.yahya.task.tracker.tasktracker.service.TrackService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class TaskTrackerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(TaskTrackerApplication.class, args);
        System.out.println("Application Started at http://localhost:4200");
//        startFrame(args);
    }

    public static void startFrame(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    } else {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }
                }
//                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            MainFrame mainFrame = new MainFrame(args);
            mainFrame.setVisible(true);
        });
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
