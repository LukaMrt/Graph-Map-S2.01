package com.juka.graphmap.view;

import javax.swing.*;
import java.awt.*;

public class SwingView {

    protected JPanel buildTitle(String title, int screenNumber) {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel label = new JLabel("~ Écran n°" + screenNumber + " ~");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        panel.add(label);
        label = new JLabel(title);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 35));
        panel.add(label);

        return panel;
    }

}
