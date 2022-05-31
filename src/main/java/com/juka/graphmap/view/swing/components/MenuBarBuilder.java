package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

public class MenuBarBuilder {

    private final JMenuBar menuBar;

    private MenuBarBuilder() {
        menuBar = new JMenuBar();
    }

    public static MenuBarBuilder aMenuBar() {
        return new MenuBarBuilder();
    }

    public MenuBarBuilder add(Component component) {
        menuBar.add(component);
        return this;
    }

    public JMenuBar build() {
        return menuBar;
    }

}
