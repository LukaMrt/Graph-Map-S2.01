package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Swing JMenuBar builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class MenuBarBuilder {

    private final JMenuBar menuBar;

    private MenuBarBuilder() {
        menuBar = new JMenuBar();
    }

    /**
     * Creates a new MenuBarBuilder.
     *
     * @return a new MenuBarBuilder
     */
    public static MenuBarBuilder aMenuBar() {
        return new MenuBarBuilder();
    }

    /**
     * Add a component to the menu bar.
     *
     * @param component the component to add
     * @return the MenuBarBuilder
     */
    public MenuBarBuilder add(Component component) {
        menuBar.add(component);
        return this;
    }

    /**
     * Build the final JMenuBar.
     *
     * @return the final JMenuBar
     */
    public JMenuBar build() {
        return menuBar;
    }

}
