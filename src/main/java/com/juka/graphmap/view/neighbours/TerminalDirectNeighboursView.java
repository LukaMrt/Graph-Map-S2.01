package com.juka.graphmap.view.neighbours;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighboursView;

import java.util.List;

public class TerminalDirectNeighboursView implements DirectNeighboursView {

    @Override
    public void display(List<Node> nodes, List<String> links, NodeCharacteristics nodeCharacteristics, LinkCharacteristics linkCharacteristics) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°3 - Voisinage direct");

        System.out.println();
        System.out.println("Liste des noeuds :");
        for (Node node : nodes) {
            System.out.println("- " + node.getName());
        }

        System.out.println();
        System.out.println("Liste des liens :");
        for (String link : links) {
            System.out.println("- " + link);
        }

        if (nodeCharacteristics != null) {
            System.out.println();
            System.out.println("Informations de " + nodeCharacteristics.name + " :");
            System.out.println("\t- Type : " + nodeCharacteristics.type);
            System.out.println("\t- Voisins directs : ");
            for (String neighbor : nodeCharacteristics.neighbors) {
                System.out.println("\t\t> " + neighbor);
            }
        }

        if (linkCharacteristics != null) {
            System.out.println();
            System.out.println("Informations du lien " + linkCharacteristics.name + " :");
            System.out.println("\t- Entre " + linkCharacteristics.start + " et " + linkCharacteristics.end);
            System.out.println("\t- Type : " + linkCharacteristics.type);
            System.out.println("\t- Distance : " + linkCharacteristics.distance + " km");
        }

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Quitter");
        System.out.println("1 - Choisir 1 noeud à étudier");
        System.out.println("2 - Choisir 1 lien à étudier");
        System.out.println("3 - Retour");
    }

}
