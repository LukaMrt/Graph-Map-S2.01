package com.juka.graphmap.view.neighbours;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.neighbours.IndirectNeighboursView;

import java.util.List;

public class TerminalIndirectNeighboursView implements IndirectNeighboursView {

    @Override
    public void display() {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°4 - Voisinage indirect");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Choisissez 2 villes à étudier");
        System.out.println("2 - Retour à l'écran principal");

    }

    @Override
    public void displayNodes(List<Node> nodes) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°5 - Affichage des noeuds");
        for (Node node : nodes) {
            System.out.println(node.getName());
        }

    }

    @Override
    public void displayResult(String location1, String location2, boolean result) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Résultat");
        System.out.println(result ? location1 + " et " + location2 + " sont à 2-distance." : location1 + " et " + location2 + " ne sont pas à 2-distance.");

    }

}
