package com.juka.graphmap.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.main.guice.NewSwingGuiceModule;
import com.juka.graphmap.main.guice.SwingGuiceModule;
import com.juka.graphmap.main.guice.TerminalGuiceModule;
import com.juka.graphmap.ui.home.HomeUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        String file = JOptionPane.showInputDialog(null, "Entrez le nom du fichier du graphe", "Graphe", JOptionPane.QUESTION_MESSAGE);

        int i = JOptionPane.showOptionDialog(null, "Choisissez votre mode d'affichage", "GraphMap", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"Terminal", "Interface graphique sans clic sur le graphe", "Interface graphique avec clic sur le graphe"}, "Terminal");

        Injector injector = switch (i) {
            case 1 -> Guice.createInjector(new SwingGuiceModule(file));
            case 2 -> Guice.createInjector(new NewSwingGuiceModule(file));
            default -> Guice.createInjector(new TerminalGuiceModule(file));
        };

        injector.getInstance(GraphService.class).load();
        injector.getInstance(PathService.class).computeFloydWarshall();
        injector.getInstance(HomeUI.class).interact();

    }

}
