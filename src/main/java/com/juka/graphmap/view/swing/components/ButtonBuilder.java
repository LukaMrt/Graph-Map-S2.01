package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonBuilder {

    private final JButton button;

    public ButtonBuilder() {
        button = new JButton();
    }

    public static ButtonBuilder aButton() {
        return new ButtonBuilder();
    }

    public ButtonBuilder withText(String text) {
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        return this;
    }

    public ButtonBuilder withSize(int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setSize(new Dimension(width, height));
        return this;
    }

    public ButtonBuilder withAction(ActionListener listener) {
        button.addActionListener(listener);
        return this;
    }

    public ButtonBuilder isYCentered() {
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    public ButtonBuilder isXCentered() {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    public JButton build() {
        return button;
    }

}
