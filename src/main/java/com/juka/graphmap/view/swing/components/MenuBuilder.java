package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

public class MenuBuilder {

    private final JMenu menu;

    private MenuBuilder() {
        menu = new JMenu();
    }

    public static MenuBuilder aMenu() {
        return new MenuBuilder();
    }


    public MenuBuilder withText(String title) {
        menu.setText(title);
        return this;
    }

    public MenuBuilder add(Component component) {
        menu.add(component);
        return this;
    }

    public JMenu build() {
        return menu;
    }

}
