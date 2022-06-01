package com.juka.graphmap.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.main.guice.SwingGuiceModule;
import com.juka.graphmap.main.guice.TerminalGuiceModule;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.view.welcome.StartFrame;
import com.juka.graphmap.view.welcome.ViewType;

public class Main {

    public static void main(String[] args) {
        new Main().setUp();
    }

    private void setUp() {
        new StartFrame(this).display();
    }

    public void start(ViewType choice, String file) {

        Injector injector = Guice.createInjector(new TerminalGuiceModule(file));

        if (choice == ViewType.GRAPHICAL_INTERFACE) {
            injector = Guice.createInjector(new SwingGuiceModule(file));
        }

        injector.getInstance(GraphService.class).load();
        injector.getInstance(PathService.class).computeFloydWarshall();
        injector.getInstance(HomeUI.class).interact();
    }

}
