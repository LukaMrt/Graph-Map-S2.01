package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Swing JLabel builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class LabelBuilder {

    private final JLabel label;

    private LabelBuilder() {
        this.label = new JLabel();
    }

    /**
     * Creates a new LabelBuilder.
     *
     * @return a new LabelBuilder
     */
    public static LabelBuilder aLabel() {
        return new LabelBuilder();
    }

    /**
     * Set the text of the label.
     *
     * @param text the text of the label
     * @return the LabelBuilder
     */
    public LabelBuilder withText(String text) {
        this.label.setText(text);
        return this;
    }

    /**
     * Set the font of the label.
     *
     * @param font the font of the label
     * @return the LabelBuilder
     */
    public LabelBuilder withFont(Font font) {
        this.label.setFont(font);
        return this;
    }

    /**
     * Set the font with big title size.
     *
     * @return the LabelBuilder
     */
    public LabelBuilder isBigTitle() {
        return withFont(new Font("Arial", Font.PLAIN, 35));
    }

    /**
     * Set the font with subtitle of big title size.
     *
     * @return the LabelBuilder
     */
    public LabelBuilder isSubBigTitle() {
        return withFont(new Font("Arial", Font.BOLD, 35));
    }

    /**
     * Set the font with title size.
     *
     * @return the LabelBuilder
     */
    public LabelBuilder isTitle() {
        return withFont(new Font("Arial", Font.PLAIN, 19));
    }

    /**
     * Set the font with text size.
     *
     * @return the LabelBuilder
     */
    public LabelBuilder isText() {
        return withFont(new Font("Arial", Font.PLAIN, 16));
    }

    /**
     * Center the label in X axis.
     *
     * @return the LabelBuilder
     */
    public LabelBuilder isXCentered() {
        this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Center the label in Y axis.
     *
     * @return the LabelBuilder
     */
    public LabelBuilder isYCentered() {
        this.label.setAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Set the color of the label.
     *
     * @param color the new color of the label
     * @return the LabelBuilder
     */
    public LabelBuilder withColor(Color color) {
        this.label.setForeground(color);
        return this;
    }

    /**
     * Set the size of the label.
     *
     * @param width  the width of the label
     * @param height the height of the label
     * @return the LabelBuilder
     */
    public LabelBuilder withSize(int width, int height) {
        this.label.setPreferredSize(new Dimension(width, height));
        this.label.setSize(new Dimension(width, height));
        this.label.setMinimumSize(new Dimension(width, height));
        this.label.setMaximumSize(new Dimension(width, height));
        return this;
    }

    /**
     * Build the final JLabel.
     *
     * @return the final JLabel
     */
    public JLabel build() {
        return this.label;
    }

}
