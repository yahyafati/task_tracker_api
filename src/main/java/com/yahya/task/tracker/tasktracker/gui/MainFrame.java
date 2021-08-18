package com.yahya.task.tracker.tasktracker.gui;

import com.yahya.task.tracker.tasktracker.TaskTrackerApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;

public class MainFrame extends JFrame {

    private final String[] args;
    private final MainFrame mainFrame;
    PopupMenu popupMenu;
    JLabel status;

    JButton startServiceButton;
    JButton stopServiceButton;
    TrayIcon trayIcon = null;
    private Status currentStatus;


    enum Status {
        RUNNING("Running"), STARTING("Starting"), SHUTTING("Shutting Down"), CLOSED("Closed");

        private final String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public String displayStatus() {
            return "Server is " + status;
        }

    }

    private ConfigurableApplicationContext ctx;
    public MainFrame(String[] args) {
        this.args = args;
        mainFrame = this;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(600, 400));
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Task Tracker Server");
        init();
        initEvents();
        initTray();
    }

    private void exitWindow() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void showWindow() {
        setVisible(true);
        this.setState(JFrame.NORMAL);
    }

//    @SneakyThrows
//    private ImageIcon getImageResource(String resourceName) {
//        return new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(resourceName))));
//    }

    private void initTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("/trayIcon.ico");
            popupMenu = new PopupMenu();

            MenuItem startItem = new MenuItem("Start Server");
            startItem.addActionListener(e -> startService());
            popupMenu.add(startItem);

            MenuItem stopItem = new MenuItem("Stop Server");
            stopItem.addActionListener(e -> stopService());
            popupMenu.add(stopItem);

            MenuItem restartItem = new MenuItem("Restart Service");
            restartItem.addActionListener(e -> {
                stopService();
                startService();
            });

            popupMenu.add(restartItem);

            popupMenu.addSeparator();

            MenuItem defaultItem = new MenuItem("Show Window");
            defaultItem.addActionListener(e -> showWindow());
            popupMenu.add(defaultItem);

            MenuItem exitItem = new MenuItem("Exit Window");
            exitItem.addActionListener(e -> exitWindow());
            popupMenu.add(exitItem);

            trayIcon = new TrayIcon(image, "Task Tracker Server", popupMenu);
            trayIcon.addActionListener(e -> showWindow());

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    private void initEvents() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (ctx != null && (ctx.isActive() || ctx.isRunning())) {
                    int response = JOptionPane.showConfirmDialog(mainFrame, "The server is active. Are you sure you want to close?",
                            "You sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (response == JOptionPane.YES_OPTION) {
                        stopService();
                    } else {
                        return;
                    }
                }
                if (SystemTray.isSupported()) {
                    SystemTray.getSystemTray().remove(trayIcon);
                }
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                if (ctx != null && (ctx.isActive() || ctx.isRunning())) {
                    ctx.close();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                setVisible(false);
            }
        });
    }

    void stopService() {
        Executors.newSingleThreadExecutor().execute(() -> {
            setCurrentStatus(Status.SHUTTING);
            if (ctx != null) {
                ctx.close();
                setCurrentStatus(Status.CLOSED);
                System.out.println("Active: " + ctx.isActive());
                System.out.println("Running: " + ctx.isRunning());
            }
            ctx = null;
            startServiceButton.setEnabled(true);
        });
    }

    void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
        status.setText(currentStatus.displayStatus());
        trayIcon.setToolTip("Task Tracker Server - [" + currentStatus.getStatus() + "]");
    }

    void startService() {
        Executors.newSingleThreadExecutor().execute(() -> {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            setCurrentStatus(Status.STARTING);
            startServiceButton.setEnabled(false);
            stopServiceButton.setEnabled(false);
            if (ctx == null) {
                ctx = SpringApplication.run(TaskTrackerApplication.class, args);
                status.setText("Server is Running");
                setCurrentStatus(Status.RUNNING);
            }
            System.out.println("Active: " + ctx.isActive());
            System.out.println("Running: " + ctx.isRunning());
            stopServiceButton.setEnabled(true);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        });
    }

    void initButtonSize(JButton button) {
        button.setPreferredSize(new Dimension(200, 50));
        button.setMinimumSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setSize(new Dimension(200, 50));
    }

    void init() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

        status = new JLabel("Not Running");
        status.setFont(status.getFont().deriveFont(18f));
        startServiceButton = new JButton("Start");
        initButtonSize(startServiceButton);
        startServiceButton.addActionListener(e -> startService());

        stopServiceButton = new JButton("Stop");
        initButtonSize(stopServiceButton);
        stopServiceButton.addActionListener(e -> stopService());

        JButton exitServiceButton = new JButton("Exit");
        exitServiceButton.addActionListener(e -> exitWindow());
        initButtonSize(exitServiceButton);

        buttons.add(status);
        buttons.add(startServiceButton);
        buttons.add(stopServiceButton);
        buttons.add(exitServiceButton);

        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.setAlignmentY(Component.CENTER_ALIGNMENT);
        contentPane.add(buttons);

        contentPane.setPreferredSize(getSize());
        setContentPane(contentPane);
    }

}
