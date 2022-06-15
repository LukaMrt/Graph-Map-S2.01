package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Swing JMenu builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class MenuBuilder {

    private final JMenu menu;

    private MenuBuilder() {
        menu = new JMenu();
    }

    /**
     * Creates a new MenuBuilder.
     *
     * @return a new MenuBuilder
     */
    public static MenuBuilder aMenu() {
        return new MenuBuilder();
    }

    /**
     * Set the text of the menu.
     *
     * @param title the text of the menu
     * @return the MenuBuilder
     */
    public MenuBuilder withText(String title) {
        menu.setText(title);
        return this;
    }

    /**
     * Add a component to the menu.
     *
     * @param component the component to add
     * @return the MenuBuilder
     */
    public MenuBuilder add(Component component) {
        menu.add(component);
        return this;
    }

    /**
     * Build the final JMenu.
     *
     * @return the final JMenu
     */
    public JMenu build() {
        return menu;
    }

}
