package com.juka.graphmap.ui.roads;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.application.path.RoadsFinderService;
import com.juka.graphmap.domain.model.road.Road;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.List;
import java.util.Scanner;

public class TerminalRoadsUI implements RoadsUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final RoadsFinderService pathService;
    private final RoadsView view;
    private final HomeUI homeUI;

    @Inject
    public TerminalRoadsUI(PathService pathService, RoadsView view, HomeUI homeUI) {
        this.pathService = pathService;
        this.view = view;
        this.homeUI = homeUI;
    }

    @Override
    public void interact(int cities, int restaurants, int recreationCenters) {

        List<Road> roads = pathService.getPathsWithSpecificLocations(cities, restaurants, recreationCenters);
        view.display(roads, cities, restaurants, recreationCenters);

        char entry = SCANNER.nextLine().charAt(0);

        while (!"012".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1 ou 2).");
            SCANNER.nextLine();
        }

        switch (entry) {
            case '0' -> System.out.println("Au revoir.");
            case '1' -> homeUI.interact();
            default -> {}
        }

    }

}
