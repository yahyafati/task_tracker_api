package com.yahya.task.tracker.tasktracker.gui;

import javax.swing.*;
import java.awt.*;

public class StatusLabel extends JLabel {

    public StatusLabel(Icon icon) {
        super("", icon, CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getIcon() != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            ImageIcon icon = (ImageIcon) getIcon();
            int h = icon.getImage().getHeight(null);
            int w = icon.getImage().getWidth(null);

            int startX = getWidth() / 2 - w / 2;
            int startY = getHeight() / 2 - h / 2;

            g2.drawImage(icon.getImage(), startX, startY, null);
        }
    }
}
