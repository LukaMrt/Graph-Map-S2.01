package com.juka.graphmap.view.path;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.step.Step;
import com.juka.graphmap.ui.path.PathView;

import java.util.List;

public class TerminalPathView implements PathView {

    @Override
    public void display(List<Node> nodes, Node node1, Node node2, Path path) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Recherche de chemins");

        System.out.println();
        System.out.println("Liste des noeuds :");
        for (Node node : nodes) {
            System.out.println("- " + node.getName());
        }

        if (node1 != null && node2 != null) {
            System.out.println();
            System.out.println("Chemin le plus court entre " + node1.getName() + " et " + node2.getName() + "  (" + path.getDistance() + " km) :");

            System.out.println("=> " + path.getPath().get(0).getDestination().getName());

            for (Step step : path.getPath().subList(1, path.getPath().size())) {
                System.out.println("=> " + step.getDestination().getName() + ", via " + step.getOriginLink().getRoadNameWithIndex());
            }

        }

        System.out.println();
        System.out.println("0 - Retour");
        System.out.println("1 - Rechercher un chemin");
        System.out.println("2 - Retour à l'écran principal");
    }

}
