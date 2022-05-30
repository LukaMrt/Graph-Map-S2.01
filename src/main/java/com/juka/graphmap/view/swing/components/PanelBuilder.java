package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelBuilder {

    private final JPanel panel;

    private PanelBuilder() {
        this.panel = new JPanel();
    }

    private PanelBuilder(JPanel panel) {
        this.panel = panel;
    }

    public static PanelBuilder aPanel() {
        return new PanelBuilder();
    }

    public static PanelBuilder aPanel(JPanel panel) {
        return new PanelBuilder(panel);
    }

    public PanelBuilder withLayout(LayoutManager layout) {
        this.panel.setLayout(layout);
        return this;
    }

    public PanelBuilder withBorderLayout() {
        return withLayout(new BorderLayout());
    }

    public PanelBuilder withYBoxLayout() {
        return withLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    public PanelBuilder withXBoxLayout() {
        return withLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    public PanelBuilder withAlignmentX(float alignmentX) {
        this.panel.setAlignmentX(alignmentX);
        return this;
    }

    public PanelBuilder isXCentered() {
        this.withAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    private PanelBuilder withAlignmentY(float alignmentY) {
        this.panel.setAlignmentY(alignmentY);
        return this;
    }

    public PanelBuilder isYCentered() {
        this.withAlignmentY(Component.CENTER_ALIGNMENT);
        return this;
    }

    public PanelBuilder addHorizontalGlue() {
        this.panel.add(Box.createHorizontalGlue());
        return this;
    }

    public PanelBuilder addVerticalGlue() {
        this.panel.add(Box.createVerticalGlue());
        return this;
    }

    public PanelBuilder addRigidArea(Dimension dimension) {
        this.panel.add(Box.createRigidArea(dimension));
        return this;
    }

    public PanelBuilder addRigidArea(int width, int height) {
        return addRigidArea(new Dimension(width, height));
    }

    public PanelBuilder add(Component component) {
        this.panel.add(component);
        return this;
    }

    public PanelBuilder add(Component component, String location) {
        this.panel.add(component, location);
        return this;
    }

    public PanelBuilder addAll(List<? extends Component> components) {
        components.forEach(this::add);
        return this;
    }

    public PanelBuilder addAllFollowedByHorizontalGlue(List<? extends Component> components) {
        for (Component component : components) {
            add(component).addHorizontalGlue();
        }
        return this;
    }

    public PanelBuilder withSize(int width, int height) {
        this.panel.setPreferredSize(new Dimension(width, height));
        this.panel.setMinimumSize(new Dimension(width, height));
        this.panel.setMaximumSize(new Dimension(width, height));
        this.panel.setSize(new Dimension(width, height));
        return this;
    }

    public PanelBuilder withFilledBorders() {
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return this;
    }

    public JPanel build() {
        return this.panel;
    }

}
