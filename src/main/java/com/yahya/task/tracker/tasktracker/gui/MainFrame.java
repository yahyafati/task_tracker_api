package com.yahya.task.tracker.tasktracker.gui;

import com.yahya.task.tracker.tasktracker.TaskTrackerApplication;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {

    private final String[] args;
    private final MainFrame mainFrame;
    PopupMenu popupMenu;
    StatusLabel status;

    Button startServiceButton;
    Button stopServiceButton;
    TrayIcon trayIcon = null;

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

        workOnArgs();
    }

    private void workOnArgs() {
        ArrayList<String> argsList = (ArrayList<String>) Arrays.stream(args).collect(Collectors.toList());
        if (argsList.contains("--startup")) {
            setState(JFrame.ICONIFIED);
            startService();
        } else if (argsList.contains("--start")) {
            startService();
        }
    }

    private void exitWindow() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void showWindow() {
        setVisible(true);
        this.setState(JFrame.NORMAL);
    }

    @SneakyThrows
    private BufferedImage getImageResource(String resourceName) {
        return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(resourceName)));
    }

    private void initTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            BufferedImage bufferedImage = getImageResource("/images/statusIcons/server.png");
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

            trayIcon = new TrayIcon(bufferedImage, "Task Tracker Server", popupMenu);
            trayIcon.setImageAutoSize(true);
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
                System.exit(0);
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
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            setCurrentStatus(Status.SHUTTING);
            if (ctx != null) {
                ctx.close();
                System.out.println("Active: " + ctx.isActive());
                System.out.println("Running: " + ctx.isRunning());
            }
            ctx = null;
            setCurrentStatus(Status.CLOSED);
            startServiceButton.setEnabled(true);
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        });
    }

    void setCurrentStatus(Status currentStatus) {
        status.setToolTipText(currentStatus.displayStatus());
        trayIcon.setToolTip("Task Tracker Server - [" + currentStatus.getStatus() + "]");

        ImageIcon imageIcon;
        if (currentStatus == Status.RUNNING) {
            imageIcon = new ImageIcon(getImageResource("/images/statusIcons/running_server.png"));
        } else if (currentStatus == Status.CLOSED) {
            imageIcon = new ImageIcon(getImageResource("/images/statusIcons/closed_server.png"));
        } else if (currentStatus == Status.SHUTTING || currentStatus == Status.STARTING) {
            imageIcon = new ImageIcon(getImageResource("/images/statusIcons/loading_server.png"));
        } else {
            imageIcon = new ImageIcon(getImageResource("/images/statusIcons/server.png"));
        }

        if (trayIcon != null) {
            trayIcon.setImage(imageIcon.getImage());
        }
        setIconImage(imageIcon.getImage());
        status.setIcon(imageIcon);
    }

    void startService() {
        Executors.newSingleThreadExecutor().execute(() -> {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            startServiceButton.setEnabled(false);
            stopServiceButton.setEnabled(false);
            if (ctx == null) {
                setCurrentStatus(Status.STARTING);
                ctx = SpringApplication.run(TaskTrackerApplication.class, args);
                setCurrentStatus(Status.RUNNING);
            }
            System.out.println("Active: " + ctx.isActive());
            System.out.println("Running: " + ctx.isRunning());
            stopServiceButton.setEnabled(true);
            stopServiceButton.requestFocus();
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        });
    }

    void init() {
        ImageIcon icon = new ImageIcon(getImageResource("/images/statusIcons/server.png"));
        setIconImage(icon.getImage());

        ImagePanel contentPane = new ImagePanel();
        contentPane.setLayout(new GridLayout());
        status = new StatusLabel(
                new ImageIcon(
                        getImageResource("/images/statusIcons/server.png")
                                .getScaledInstance(128, 128, Image.SCALE_DEFAULT)
                ));
        status.setBackground(Color.decode("#eeeeee"));
        status.setOpaque(true);

        JPanel buttonsPanelContainer = new JPanel(new GridBagLayout());
        JPanel buttons = new JPanel();

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));
        buttons.setBounds(0,0, getWidth(), getHeight());



        startServiceButton = new Button("Start",
                new ImageIcon(getImageResource("/images/icons/start.png")));
        startServiceButton.addActionListener(e -> startService());

        stopServiceButton = new Button("Stop",
                new ImageIcon(getImageResource("/images/icons/stop.png")));
        stopServiceButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to stop the server?",
                    "You sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                stopService();
            }
        });

        Button exitServiceButton = new Button("Exit", new ImageIcon(getImageResource("/images/icons/exit.png")));
        exitServiceButton.addActionListener(e -> exitWindow());

        buttons.add(startServiceButton);
        buttons.add(stopServiceButton);
        buttons.add(exitServiceButton);

        buttonsPanelContainer.add(buttons);

        contentPane.add(status);
        contentPane.add(buttonsPanelContainer);

        contentPane.setPreferredSize(getSize());
        setContentPane(contentPane);
    }

}
