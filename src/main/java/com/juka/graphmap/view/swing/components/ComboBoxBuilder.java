package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Swing JComboBox builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class ComboBoxBuilder {

    private final JComboBox<String> comboBox;

    private ComboBoxBuilder() {
        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    /**
     * Creates a new ComboBoxBuilder.
     *
     * @return a new ComboBoxBuilder
     */
    public static ComboBoxBuilder aComboBox() {
        return new ComboBoxBuilder();
    }

    /**
     * Sets the ComboBox data.
     *
     * @param data the ComboBox data
     * @return the ComboBoxBuilder
     */
    public ComboBoxBuilder withData(List<String> data) {
        data.forEach(comboBox::addItem);
        return this;
    }

    /**
     * Sets the ComboBox size.
     *
     * @param width  the ComboBox width
     * @param height the ComboBox height
     * @return the ComboBoxBuilder
     */
    public ComboBoxBuilder withSize(int width, int height) {
        comboBox.setPreferredSize(new Dimension(width, height));
        comboBox.setMaximumSize(new Dimension(width, height));
        comboBox.setMinimumSize(new Dimension(width, height));
        comboBox.setSize(new Dimension(width, height));
        return this;
    }

    /**
     * Sets the ComboBox selected value.
     *
     * @param value the ComboBox selected value
     * @return the ComboBoxBuilder
     */
    public ComboBoxBuilder withSelectedValue(String value) {
        comboBox.setSelectedItem(value);
        return this;
    }

    /**
     * Centers the ComboBox in the X axis.
     *
     * @return the ComboBoxBuilder
     */
    public ComboBoxBuilder isXCentered() {
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Get the ComboBox selected value.
     *
     * @return the ComboBox selected value
     */
    public String getSelectedValue() {
        return (String) comboBox.getSelectedItem();
    }

    /**
     * Get the final JComboBox.
     *
     * @return the final JComboBox
     */
    public JComboBox<String> build() {
        return comboBox;
    }

}
