package com.juka.graphmap.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.main.guice.SwingGuiceModule;
import com.juka.graphmap.main.guice.TerminalGuiceModule;
import com.juka.graphmap.ui.home.HomeUI;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TerminalGuiceModule("graph.csv"));
        injector.getInstance(GraphService.class).load();
        injector.getInstance(HomeUI.class).interact();

    }

}
