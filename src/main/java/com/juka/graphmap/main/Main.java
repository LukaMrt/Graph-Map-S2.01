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

/**
 * Main class of the application.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
public class Main {

    /**
     * Main method of the application.
     *
     * @param args arguments of the application
     */
    public static void main(String[] args) {
        new Main().launchStartScreen();
    }

    /**
     * Sets up the frame to choose the view.
     */
    private void launchStartScreen() {
        new StartFrame(this).display((viewType, file) -> new Thread(() -> this.start(viewType, file)));
    }

    /**
     * Starts the application with the given view.
     *
     * @param choice choice of the view
     * @param file   path to the graph file
     */
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
