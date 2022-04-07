package com.juka.graphmap.view.graph;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphView;

import java.util.List;

public class TerminalGraphView implements GraphView {

    @Override
    public void displayMenu() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Choix des informations à afficher");
        System.out.println();

        System.out.println("0 - Quitter");
        System.out.println("1 - Afficher les noeuds");
        System.out.println("2 - Afficher les liens");
    }

    @Override
    public void displayNodes(List<Node> nodes) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Affichage de tous les noeuds");
        System.out.println();
        for (Node node : nodes) {
            System.out.println(node.getName());
        }
        System.out.println();
        System.out.println("0 - Quitter");
        System.out.println("1 - Afficher les liens");
    }

    @Override
    public void displayLinks(List<Link> links) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Affichage de tous les noeuds");
        System.out.println();
        for (Link link : links) {
            System.out.println(link.getName() + "\n" +
                    "\tDestination : " + link.getDestination().getName() + "\n" +
                    "\tType : " + link.getType() + "\n" +
                    "\tDistance : " + link.getDistance());
        }
        System.out.println();
        System.out.println("0 - Quitter");
        System.out.println("1 - Afficher les noeuds");
    }
}
