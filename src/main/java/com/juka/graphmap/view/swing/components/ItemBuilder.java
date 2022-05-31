package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ItemBuilder {

    private final JMenuItem item;

    private ItemBuilder() {
        item = new JMenuItem();
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withText(String title) {
        item.setText(title);
        return this;
    }

    public ItemBuilder withListener(ActionListener actionListener) {
        item.addActionListener(actionListener);
        return this;
    }

    public JMenuItem build() {
        return item;
    }

}
