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
        System.out.println("  - " + graph.cityPercentage + " % de villes");
        System.out.println("  - " + graph.restaurantPercentage + " % de restaurants");
        System.out.println("  - " + graph.recreationPercentage + " % de centres de loisirs");
        System.out.println();

        System.out.println("Le graphe contient " + graph.roadCount + " routes dont :");
        System.out.println("  - " + graph.highwayPercentage + " % d'autoroutes");
        System.out.println("  - " + graph.nationalPercentage + " % de nationales");
        System.out.println("  - " + graph.departementalPercentage + " % de départementales");

        System.out.println("------------------------------------------------------");
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Quitter");
        System.out.println("1 - Écran n°2 (affichage complet du graphe)");
        System.out.println("2 - Écran n°7 (recherche de route)");
    }

}
