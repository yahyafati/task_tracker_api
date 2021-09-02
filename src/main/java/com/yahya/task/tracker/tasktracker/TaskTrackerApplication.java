package com.yahya.task.tracker.tasktracker;

import com.yahya.task.tracker.tasktracker.gui.MainFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class TaskTrackerApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext ctx =
//                SpringApplication.run(TaskTrackerApplication.class, args);
//        System.out.println("Application Started at http://localhost:4200");
        startFrame(args);
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

}
