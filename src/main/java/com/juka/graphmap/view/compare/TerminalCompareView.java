package com.juka.graphmap.view.compare;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareView;

import java.util.List;
import java.util.Map;

public class TerminalCompareView implements CompareView {

    @Override
    public void display() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°5 - Comparaison de villes");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Choisissez 2 villes à comparer");
        System.out.println("2 - Retour à l'écran principal");
    }

    @Override
    public void displayNodes(NodeRepository nodeRepository) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°5 - Affichage des noeuds");
        for (Node node : nodeRepository.getAllNodes()) {
            System.out.println(node.getName() + " : " + node.getType());
        }
    }

    @Override
    public void displayComparaison(Map<String, Boolean> comparaison, Node node1, Node node2) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°5 - Comparaison des villes");
        System.out.println();
        System.out.println(node1.getName() + " est plus ouverte que " +
                node2.getName() + " ? " + comparaison.get("open"));
        System.out.println(node1.getName() + " est plus culturelle que " +
                node2.getName() + " ? " + comparaison.get("cultural"));
        System.out.println(node1.getName() + " est plus gastronomique que " +
                node2.getName() + " ? " + comparaison.get("gastronomic"));
    }

}
