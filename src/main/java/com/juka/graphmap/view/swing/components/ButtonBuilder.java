package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Swing JButton builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class ButtonBuilder {

    private final JButton button;

    private ButtonBuilder() {
        button = new JButton();
    }

    /**
     * Crate a ButtonBuilder.
     *
     * @return a ButtonBuilder
     */
    public static ButtonBuilder aButton() {
        return new ButtonBuilder();
    }

    /**
     * Set the text of the Button.
     *
     * @param text the text of the Button
     * @return the ButtonBuilder
     */
    public ButtonBuilder withText(String text) {
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        return this;
    }

    /**
     * Set the size of the Button.
     *
     * @param width  the width of the Button
     * @param height the height of the Button
     * @return the ButtonBuilder
     */
    public ButtonBuilder withSize(int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setMinimumSize(new Dimension(width, height));
        button.setSize(new Dimension(width, height));
        return this;
    }

    /**
     * Set the action listener of the Button.
     *
     * @param listener the action listener of the Button
     * @return the ButtonBuilder
     */
    public ButtonBuilder withAction(ActionListener listener) {
        button.addActionListener(listener);
        return this;
    }

    /**
     * Center the Button on the Y axis.
     *
     * @return the ButtonBuilder
     */
    public ButtonBuilder isYCentered() {
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Center the Button on the X axis.
     *
     * @return the ButtonBuilder
     */
    public ButtonBuilder isXCentered() {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Build the final Button.
     *
     * @return the final Button
     */
    public JButton build() {
        return button;
    }

}
