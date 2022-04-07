package com.juka.graphmap.view.compare;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareView;

import java.util.List;
import java.util.Map;

public class TerminalCompareView implements CompareView {


    @Override
    public void displayEndOptions() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°4 - Comparaison de villes");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Retour à l'écran n°1");
    }

    @Override
    public void displayOptions() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°4 - Comparaison de villes");
        System.out.println("Choisissez 2 villes à comparer");
    }

    @Override
    public void displayNodes(List<Node> nodes) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°4 - Affichage des noeuds");
        for (Node node : nodes) {
            System.out.println(node.getName() + " : " + node.getType());
        }
    }

    @Override
    public void displayComparaison(Map<String, Boolean> comparaison, Node node1, Node node2) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°4 - Comparaison des villes");
        System.out.println();
        System.out.println(node1.getName() + " est plus ouverte que " +
                node2.getName() + " ? " + comparaison.get("open"));
        System.out.println(node1.getName() + " est plus culturelle que " +
                node2.getName() + " ? " + comparaison.get("cultural"));
        System.out.println(node1.getName() + " est plus gastronomique que " +
                node2.getName() + " ? " + comparaison.get("gastronomic"));
    }
}
