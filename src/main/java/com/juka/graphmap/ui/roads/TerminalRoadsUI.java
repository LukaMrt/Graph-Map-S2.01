package com.juka.graphmap.ui.roads;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.application.path.RoadsFinderService;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalRoadsUI implements RoadsUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final RoadsFinderService pathService;
    private final RoadsView view;
    private final HomeUI homeUI;

    @Inject
    public TerminalRoadsUI(GraphService graphService, PathService pathService, RoadsView view, HomeUI homeUI) {
        this.graphService = graphService;
        this.pathService = pathService;
        this.view = view;
        this.homeUI = homeUI;
    }

    @Override
    public void interact(String start, List<String> steps, String end) {

        Path path = null;

        if (start != null && end != null) {
            path = pathService.getPathsWithSpecificLocations(start, steps, end);
        }

        view.display(graphService.getAllNodes(), steps, path);

        char entry = SCANNER.nextLine().charAt(0);

        while (!"012".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1 ou 2).");
            SCANNER.nextLine();
        }

        switch (entry) {
            case '0' -> System.out.println("Au revoir.");
            case '1' -> this.interact(chooseLocation("le départ"), chooseSteps(), chooseLocation("la destination"));
            case '2' -> homeUI.interact();
        }

    }

    private String chooseLocation(String text) {

        System.out.println();
        String entry;
        do {
            System.out.println("Choisissez " + text + " :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return entry;
    }

    private List<String> chooseSteps() {

        System.out.println();
        String entry = ".";

        List<String> steps = new ArrayList<>();

        while (!entry.isEmpty()) {

            do {
                System.out.println("Choisissez une étape (entrée pour terminer) :");
                entry = SCANNER.nextLine();
            } while (!graphService.nodeExist(entry) && !entry.isEmpty());

            if (!entry.isEmpty()) {
                steps.add(entry);
            }

        }

        return steps;
    }

}
