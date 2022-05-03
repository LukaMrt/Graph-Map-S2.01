package com.juka.graphmap.view.neighbours;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighboursView;

import java.util.List;

public class TerminalDirectNeighboursView implements DirectNeighboursView {

    @Override
    public void display() {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°3 - Voisinage direct");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Choisissez 1 noeud à étudier");
        System.out.println("2 - Choisissez 1 lien à étudier");
        System.out.println("3 - Retour à l'écran principal");
    }

    @Override
    public void displayNodes(List<Node> nodes) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°3 - Affichage des noeuds");
        for (Node node : nodes) {
            System.out.println(node.getName());
        }

    }

    @Override
    public void displayNeighbours(List<Node> neighbours, String nodeName) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°3 - Affichage des voisins directs de " + nodeName);
        for (Node node : neighbours) {
            System.out.println(node.getName() + " : " + node.getType());
        }

    }

    @Override
    public void displayLinks(List<String> links) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°3 - Affichage des noeuds");
        for (String link : links) {
            System.out.println(link);
        }

    }

    @Override
    public void displayLinkCharacteristics(LinkCharacteristics characteristics) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°3 - Affichage des caractéristiques du lien " + characteristics.name);
        System.out.println("Extrémité 1 : " + characteristics.start);
        System.out.println("Extrémité 2 : " + characteristics.end);
        System.out.println("Distance : " + characteristics.distance);

    }

}
