package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Swing JPanel builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class PanelBuilder {

    private final JPanel panel;

    private PanelBuilder() {
        this.panel = new JPanel();
    }

    private PanelBuilder(JPanel panel) {
        this.panel = panel;
    }

    /**
     * Creates a new PanelBuilder.
     *
     * @return a PanelBuilder
     */
    public static PanelBuilder aPanel() {
        return new PanelBuilder();
    }

    /**
     * Creates a new PanelBuilder based on an existing Panel.
     *
     * @param panel the base Panel
     * @return a PanelBuilder
     */
    public static PanelBuilder aPanel(JPanel panel) {
        return new PanelBuilder(panel);
    }

    private PanelBuilder withLayout(LayoutManager layout) {
        this.panel.setLayout(layout);
        return this;
    }

    /**
     * Set the layout of the Panel to BorderLayout.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder withBorderLayout() {
        return withLayout(new BorderLayout());
    }

    /**
     * Set the layout of the Panel to Y based BoxLayout.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder withYBoxLayout() {
        return withLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    /**
     * Set the layout of the Panel to X based BoxLayout.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder withXBoxLayout() {
        return withLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    /**
     * Center the Panel in the X axis.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder isXCentered() {
        this.panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Center the Panel in the Y axis.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder isYCentered() {
        this.panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return this;
    }

    /**
     * Add an horizontal glue to the Panel.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder addHorizontalGlue() {
        this.panel.add(Box.createHorizontalGlue());
        return this;
    }

    /**
     * Add a vertical glue to the Panel.
     *
     * @return the PanelBuilder
     */
    public PanelBuilder addVerticalGlue() {
        this.panel.add(Box.createVerticalGlue());
        return this;
    }

    /**
     * Add a rigid area to the Panel.
     *
     * @param width  the width of the area
     * @param height the height of the area
     * @return the PanelBuilder
     */
    public PanelBuilder addRigidArea(int width, int height) {
        this.panel.add(Box.createRigidArea(new Dimension(width, height)));
        return this;
    }

    /**
     * Add a component to the Panel.
     *
     * @param component the component to add
     * @return the PanelBuilder
     */
    public PanelBuilder add(Component component) {
        this.panel.add(component);
        return this;
    }

    /**
     * Add a component with a specific location to the Panel.
     *
     * @param component the component to add
     * @param location  the location of the component
     * @return the PanelBuilder
     */
    public PanelBuilder add(Component component, String location) {
        this.panel.add(component, location);
        return this;
    }

    /**
     * Add a list of components to the Panel.
     *
     * @param components the components to add
     * @return the PanelBuilder
     */
    public PanelBuilder addAll(List<? extends Component> components) {
        components.forEach(this::add);
        return this;
    }

    /**
     * Add a list of components to the Panel with an horizontal glue after each component.
     *
     * @param components the components to add
     * @return the PanelBuilder
     */
    public PanelBuilder addAllFollowedByHorizontalGlue(List<? extends Component> components) {
        for (Component component : components) {
            add(component).addHorizontalGlue();
        }
        return this;
    }

    /**
     * Set the size of the Panel.
     *
     * @param width  the width of the Panel
     * @param height the height of the Panel
     * @return the PanelBuilder
     */
    public PanelBuilder withSize(int width, int height) {
        this.panel.setPreferredSize(new Dimension(width, height));
        this.panel.setMinimumSize(new Dimension(width, height));
        this.panel.setMaximumSize(new Dimension(width, height));
        this.panel.setSize(new Dimension(width, height));
        return this;
    }

    /**
     * Build the final Panel.
     *
     * @return the Panel
     */
    public JPanel build() {
        return this.panel;
    }

}
