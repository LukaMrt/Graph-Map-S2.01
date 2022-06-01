package com.juka.graphmap.view.graph;


import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphView;

import java.util.List;

/**
 * The graph view with terminal interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalGraphView implements GraphView {

    @Override
    public void display(List<Node> nodes, List<Link> links) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°2 - Choix des informations à afficher");
        System.out.println();

        System.out.println("Liste des noeuds :");
        for (Node node : nodes) {
            System.out.println("- " + node.getName());
        }
        System.out.println();

        System.out.println("Liste des routes :");
        for (Link link : links) {
            System.out.println("- " + link.getRoadNameWithIndex());
        }

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Retour");
        System.out.println("1 - Comparer 2 villes");
        System.out.println("2 - Afficher le plus court chemin entre 2 noeuds");
        System.out.println("3 - Afficher les caractéristiques d'un noeud ou d'un lien");
        System.out.println("4 - Étudier si oui ou non 2 lieux sont à 2-distance");
        System.out.println("5 - Quitter");
    }

}
