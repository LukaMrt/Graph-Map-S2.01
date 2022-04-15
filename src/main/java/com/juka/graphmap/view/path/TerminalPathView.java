package com.juka.graphmap.view.path;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.step.Step;
import com.juka.graphmap.ui.path.PathView;

import java.util.List;

public class TerminalPathView implements PathView {

    @Override
    public void display() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Recherche de chemins");
        System.out.println("0 - Retour");
        System.out.println("1 - Rechercher un chemin");
        System.out.println("2 - Retour à l'écran principal");
    }

    @Override
    public void displayNodes(NodeRepository nodeRepository) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Affichages des noeuds");
        for (Node node : nodeRepository.getAllNodes()) {
            System.out.println(node.getName() + " : " + node.getType());
        }
    }

    @Override
    public void displayPath(Path path) {
        List<Step> steps = path.getPath();
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Affichage du chemin");
        System.out.println("Depart : " + steps.get(0).getDestination().getName());
        for (int i = 1; i < steps.size(); i++) {
            System.out.println("Puis : " + steps.get(i).getDestination().getName() + " via " +
                    steps.get(i).getOriginLink().getName());
        }
        System.out.println("Avec une distance de " + path.getDistance());
    }
}
