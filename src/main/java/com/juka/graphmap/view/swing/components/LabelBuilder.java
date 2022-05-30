package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

public class LabelBuilder {

    private final JLabel label;

    private LabelBuilder() {
        this.label = new JLabel();
    }

    public static LabelBuilder aLabel() {
        return new LabelBuilder();
    }

    public LabelBuilder withText(String text) {
        this.label.setText(text);
        return this;
    }

    public LabelBuilder withFont(Font font) {
        this.label.setFont(font);
        return this;
    }

    public LabelBuilder isBigTitle() {
        return withFont(new Font("Arial", Font.PLAIN, 35));
    }

    public LabelBuilder isSubBigTitle() {
        return withFont(new Font("Arial", Font.BOLD, 35));
    }

    public LabelBuilder with20PlainFont() {
        return withFont(new Font("Arial", Font.PLAIN, 20));
    }

    public LabelBuilder isTitle() {
        return withFont(new Font("Arial", Font.PLAIN, 19));
    }

    public LabelBuilder isText() {
        return withFont(new Font("Arial", Font.PLAIN, 16));
    }



    public LabelBuilder withAlignmentX(float alignmentX) {
        this.label.setAlignmentX(alignmentX);
        return this;
    }

    public LabelBuilder isXCentered() {
        this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    public JLabel build() {
        return this.label;
    }

}
