package com.juka.graphmap.view.path;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.step.Step;
import com.juka.graphmap.ui.path.PathView;

import java.util.List;

public class TerminalPathView implements PathView {

    @Override
    public void displayChoice() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Recherche de chemins");
        System.out.println("Choisissez le depart, l'arrivée et les points de passage");
    }

    @Override
    public void displayNodes(List<Node> nodes) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Affichages des noeuds");
        for (Node node : nodes) {
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

    @Override
    public void displayEndMenu() {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°6 - Fin de l'affichage");
        System.out.println("0 - Retour à l'écran précédent");
        System.out.println("1 - Retour à l'écran n°1");
    }
}
