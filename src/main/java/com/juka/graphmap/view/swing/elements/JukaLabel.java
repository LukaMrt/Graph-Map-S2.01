package com.juka.graphmap.view.swing.elements;

import javax.swing.*;
import java.awt.*;

public class JukaLabel {

    private final JLabel label;

    private JukaLabel() {
        this.label = new JLabel();
    }

    public static JukaLabel aLabel() {
        return new JukaLabel();
    }

    public JukaLabel withText(String text) {
        this.label.setText(text);
        return this;
    }

    public JukaLabel withFont(Font font) {
        this.label.setFont(font);
        return this;
    }

    public JukaLabel withBigPlainFont() {
        return withFont(new Font("Arial", Font.PLAIN, 35));
    }

    public JukaLabel withBigBoldPlainFont() {
        return withFont(new Font("Arial", Font.BOLD, 35));
    }

    public JukaLabel withAlignmentX(float alignmentX) {
        this.label.setAlignmentX(alignmentX);
        return this;
    }

    public JukaLabel centerX() {
        this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    public JLabel build() {
        return this.label;
    }

}
