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

/**
 * Terminal implementation of the roads UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalRoadsUI implements RoadsUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final RoadsFinderService pathService;
    private final RoadsView view;
    private final HomeUI homeUI;

    /**
     * Constructor of the terminal roads UI.
     *
     * @param graphService The graph service.
     * @param pathService  The path service.
     * @param view         The roads view.
     * @param homeUI       The home UI.
     */
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

        view.display(graphService.getAllNodes(), steps, path, start, end);

        char entry = SCANNER.nextLine().charAt(0);

        while (!"012".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1 ou 2).");
            SCANNER.nextLine();
        }

        switch (entry) {
            case '0':
                System.out.println("Au revoir.");
                break;
            case '1':
                this.interact(chooseLocation("le départ"), chooseSteps(), chooseLocation("la destination"));
                break;
            default:
                homeUI.interact();
        }

    }

    /**
     * Asks a user to choose a location.
     *
     * @param text The text to display to the user.
     * @return The location chosen by the user.
     */
    private String chooseLocation(String text) {

        System.out.println();
        String entry;
        do {
            System.out.println("Choisissez " + text + " :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return entry;
    }

    /**
     * Asks a user to choose a list of steps.
     *
     * @return The list of steps chosen by the user.
     */
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
