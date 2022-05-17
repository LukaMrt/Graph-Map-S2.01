package com.juka.graphmap.view.roads;

import com.juka.graphmap.domain.model.road.Road;
import com.juka.graphmap.ui.roads.RoadsView;

import java.util.List;

public class TerminalRoadsView implements RoadsView {

    @Override
    public void display(List<Road> roads, int cities, int restaurants, int recreationCenters) {

        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("Écran n°7 - Routes");
        System.out.println("Recherche de routes passant par " + cities + " villes, " + restaurants
                + " restaurants, " + recreationCenters + " centres de loisirs");

        System.out.println();
        System.out.println("Nombre de routes trouvées : " + roads.size());

        int i = 1;
        for (Road road : roads) {
            System.out.println();
            System.out.println("Route " + i + " :");
            System.out.println(road.getName() + " (" + road.countCities() + " villes, " + road.countRestaurants()
                    + " restaurants, " + road.countRecreationCenters() + " centres de loisirs)");
        }

        System.out.println();
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 - Quitter");
        System.out.println("1 - Écran n°1 (accueil)");

    }

}
