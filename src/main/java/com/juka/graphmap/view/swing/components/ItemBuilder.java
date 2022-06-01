package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Swing JMenuItem builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class ItemBuilder {

    private final JMenuItem item;

    private ItemBuilder() {
        item = new JMenuItem();
    }

    /**
     * Creates a new JMenuItem builder.
     *
     * @return a new JMenuItem builder
     */
    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    /**
     * Set the text of the MenuItem.
     *
     * @param title the text of the JMenuItem
     * @return the MenuItemBuilder
     */
    public ItemBuilder withText(String title) {
        item.setText(title);
        return this;
    }

    /**
     * Set the action of the MenuItem.
     *
     * @param actionListener the action of the JMenuItem
     * @return the MenuItemBuilder
     */
    public ItemBuilder withListener(ActionListener actionListener) {
        item.addActionListener(actionListener);
        return this;
    }

    /**
     * Build the final JMenuItem.
     *
     * @return the final JMenuItem
     */
    public JMenuItem build() {
        return item;
    }

}
