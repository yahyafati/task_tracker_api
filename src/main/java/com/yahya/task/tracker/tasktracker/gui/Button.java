package com.yahya.task.tracker.tasktracker.gui;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button() {
        this("", null);
    }

    public Button(String text) {
        this(text, null);
    }

    public Button(String text, Icon icon) {
        super(text, icon);
        initButton();
    }

    public void setSizeForced(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
    }

    private void initButton() {
//        setIcon(new ImageIcon(
//                ((ImageIcon) getIcon())
//                        .getImage()
//                        .getScaledInstance(16,16, Image.SCALE_DEFAULT)
//        ));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSizeForced(200, 50);
    }

    @Override
    protected void paintChildren(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        if (getIcon() != null) {
//
//            ImageIcon icon = (ImageIcon) getIcon();
//            int h = icon.getImage().getHeight(null);
//            int w = icon.getImage().getWidth(null);
//
//            int startX = getWidth()/2 - w/2;
//            int startY = getHeight()/2 - h/2;
//
//            g2.drawImage(icon.getImage(), startX, startY, null);
//        }
    }
}
