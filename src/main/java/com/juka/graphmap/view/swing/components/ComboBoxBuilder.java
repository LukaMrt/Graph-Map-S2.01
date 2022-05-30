package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComboBoxBuilder {

    private final JComboBox<String> comboBox;

    private ComboBoxBuilder() {
        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    public static ComboBoxBuilder aComboBox() {
        return new ComboBoxBuilder();
    }

    public ComboBoxBuilder withData(List<String> data) {
        data.forEach(comboBox::addItem);
        return this;
    }

    public ComboBoxBuilder withSize(int width, int height) {
        comboBox.setPreferredSize(new Dimension(width, height));
        comboBox.setMaximumSize(new Dimension(width, height));
        comboBox.setMinimumSize(new Dimension(width, height));
        comboBox.setSize(new Dimension(width, height));
        return this;
    }

    public ComboBoxBuilder withSelectedValue(String value) {
        comboBox.setSelectedItem(value);
        return this;
    }

    public ComboBoxBuilder isYCentered() {
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    public String getSelectedValue() {
        return (String) comboBox.getSelectedItem();
    }

    public JComboBox<String> build() {
        return comboBox;
    }

}
