package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.home.HomeView;

import java.util.List;

/**
 * The home view with terminal interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalHomeView implements HomeView {

    @Override
    public void display(GraphCharacteristics graphCharacteristics, List<Node> nodes) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°1 - Accueil");
        System.out.println(graphCharacteristics.error);

        System.out.println();
        System.out.println("Le graphe contient " + graphCharacteristics.locationCount + " lieux dont :");
        System.out.println("\t- " + graphCharacteristics.cityPercentage + " % de villes");
        System.out.println("\t- " + graphCharacteristics.restaurantPercentage + " % de restaurants");
        System.out.println("\t- " + graphCharacteristics.recreationPercentage + " % de centres de loisirs");

        System.out.println();
        System.out.println("Le graphe contient " + graphCharacteristics.roadCount + " routes dont :");
        System.out.println("\t- " + graphCharacteristics.highwayPercentage + " % d'autoroutes");
        System.out.println("\t- " + graphCharacteristics.nationalPercentage + " % de nationales");
        System.out.println("\t- " + graphCharacteristics.departementalPercentage + " % de départementales");

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Quitter");
        System.out.println("1 - Afficher le graphe complet");
        System.out.println("2 - Rechercher des routes");
    }

}
