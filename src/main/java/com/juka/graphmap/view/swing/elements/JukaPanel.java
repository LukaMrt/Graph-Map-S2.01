package com.juka.graphmap.view.swing.elements;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JukaPanel {

    private final JPanel panel;

    private JukaPanel() {
        this.panel = new JPanel();
    }

    public static JukaPanel aPanel() {
        return new JukaPanel();
    }

    public JukaPanel withLayout(LayoutManager layout) {
        this.panel.setLayout(layout);
        return this;
    }

    public JukaPanel withBorderLayout() {
        return withLayout(new BorderLayout());
    }

    public JukaPanel withYBoxLayout() {
        return withLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    public JukaPanel withXBoxLayout() {
        return withLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    public JukaPanel withAlignmentX(float alignmentX) {
        this.panel.setAlignmentX(alignmentX);
        return this;
    }

    public JukaPanel centerX() {
        this.withAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    private JukaPanel withAlignmentY(float alignmentY) {
        this.panel.setAlignmentY(alignmentY);
        return this;
    }

    public JukaPanel centerY() {
        this.withAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    public JukaPanel addHorizontalGlue() {
        this.panel.add(Box.createHorizontalGlue());
        return this;
    }

    public JukaPanel addVerticalGlue() {
        this.panel.add(Box.createVerticalGlue());
        return this;
    }

    public JukaPanel addRigidArea(Dimension dimension) {
        this.panel.add(Box.createRigidArea(dimension));
        return this;
    }

    public JukaPanel addRigidArea(int width, int height) {
        return addRigidArea(new Dimension(width, height));
    }

    public JukaPanel add(Component component) {
        this.panel.add(component);
        return this;
    }

    public JukaPanel add(Component component, String location) {
        this.panel.add(component, location);
        return this;
    }

    public JPanel build() {
        return this.panel;
    }

    public JukaPanel addAllFollowedByHorizontalGlue(List<? extends Component> components) {
        for (Component component : components) {
            add(component).addHorizontalGlue();
        }
        return this;
    }

}
