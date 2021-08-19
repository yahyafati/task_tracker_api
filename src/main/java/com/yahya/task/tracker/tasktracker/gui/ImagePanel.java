package com.yahya.task.tracker.tasktracker.gui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JComponent {
    private final Image image;

    public ImagePanel() {
        this.image = null;
    }

    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if (image != null) {
            Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
            g.drawImage(scaledImage, 0, 0, this);
        }
    }
}
