package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.ui.home.HomeView;

public class TerminalHomeView implements HomeView {

    @Override
    public void display(GraphCharacteristics graph) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°1 - Accueil");
        System.out.println(graph.error ? "Erreur détectée" : "Aucune erreur détectée");
        System.out.println();

        System.out.println("Le graphe contient " + graph.cityCount + " lieux dont :");
        System.out.println("  - " + Math.round(graph.cityPercentage * 10000) / 100.0 + " % de villes");
        System.out.println("  - " + Math.round(graph.restaurantPercentage * 10000) / 100.0 + " % de restaurants");
        System.out.println("  - " + Math.round(graph.recreationPercentage * 10000) / 100.0 + " % de centres de loisirs");
        System.out.println();

        System.out.println("Le graphe contient " + graph.roadCount + " routes dont :");
        System.out.println("  - " + Math.round(graph.highwayPercentage * 10000) / 100.0 + " % d'autoroutes");
        System.out.println("  - " + Math.round(graph.nationalPercentage * 10000) / 100.0 + " % de nationales");
        System.out.println("  - " + Math.round(graph.departementalPercentage * 10000) / 100.0 + " % de départementales");

        System.out.println("------------------------------------------------------");

        System.out.println("0 - Quitter");
        System.out.println("1 - Écran n°2 (affichage complet du graphe)");
        System.out.println("2 - Écran n°7 (recherche de route)");
    }

}
