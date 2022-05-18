package com.juka.graphmap.view.neighbours;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsView;

import java.util.List;

public class TerminalIndirectNeighborsView implements IndirectNeighborsView {

    @Override
    public void displayNodes(List<Node> nodes, String location1, String location2, boolean result) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°4 - Voisinage indirect");

        System.out.println();
        System.out.println("Liste des noeuds :");
        for (Node node : nodes) {
            System.out.println("- " + node.getName());
        }

        System.out.println();
        if (location1 != null && location2 != null) {
            System.out.println();
            System.out.println(location1 + " et " + location2 + (result ? " sont" : " ne sont pas") + " à 2-distance.");
        }

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Choisissez 2 villes à étudier");
        System.out.println("2 - Retour à l'écran principal");
    }

}
