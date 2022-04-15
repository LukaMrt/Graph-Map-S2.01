package com.juka.graphmap.view.graph;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphView;

import java.util.List;

public class TerminalGraphView implements GraphView {

    @Override
    public void display() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Choix des informations à afficher");
        System.out.println();

        System.out.println("0 - Retour");
        System.out.println("1 - Afficher les noeuds");
        System.out.println("2 - Afficher les liens");
        System.out.println("3 - Comparer 2 noeuds");
        System.out.println("4 - Afficher le plus court chemin entre 2 noeuds");
    }

    @Override
    public void displayNodes(NodeRepository nodeRepository) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Affichage de tous les noeuds");
        System.out.println();
        for (Node node : nodeRepository.getAllNodes()) {
            System.out.println(node.getName());
            System.out.println("\tType : " + node.getType());
        }
    }

    private void displayLinksHeader() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Affichage de tous les noeuds");
        System.out.println();
    }
    private void displayLink(Node origin, Link link) {
        System.out.println(link.getName().substring(0, link.getName().lastIndexOf(".")));
        System.out.println("\tOrigine : " + origin.getName());
        System.out.println("\tDestination : " + link.getDestination().getName());
        System.out.println("\tType : " + link.getType());
        System.out.println("\tDistance : " + link.getDistance());
    }

    @Override
    public void displayLinks(NodeRepository nodeRepository) {
        displayLinksHeader();
        for (Node node : nodeRepository.getAllNodes()) {
            for (Link link : node.getNeighborsLinks()) {
                displayLink(node, link);
            }
        }
    }
}
