package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.ui.home.HomeView;

public class TerminalHomeView implements HomeView {

    @Override
    public void display(GraphCharacteristics graph) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°1 - Accueil");
        System.out.println(graph.error);

        System.out.println();
        System.out.println("Le graphe contient " + graph.locationCount + " lieux dont :");
        System.out.println("\t- " + graph.cityPercentage + " % de villes");
        System.out.println("\t- " + graph.restaurantPercentage + " % de restaurants");
        System.out.println("\t- " + graph.recreationPercentage + " % de centres de loisirs");

        System.out.println();
        System.out.println("Le graphe contient " + graph.roadCount + " routes dont :");
        System.out.println("\t- " + graph.highwayPercentage + " % d'autoroutes");
        System.out.println("\t- " + graph.nationalPercentage + " % de nationales");
        System.out.println("\t- " + graph.departementalPercentage + " % de départementales");

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Quitter");
        System.out.println("1 - Afficher le graphe complet");
        System.out.println("2 - Rechercher des routes");
    }

}
