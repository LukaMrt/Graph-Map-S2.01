package com.juka.graphmap.ui.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.Scanner;

public class TerminalPathUI implements PathUI {

    public static final Scanner SCANNER = new Scanner(System.in);

    private final NodeRepository nodeRepository;
    private final PathView pathView;
    private final HomeUI homeUI;
    private final GraphUI graphUI;
    private final PathService pathService;

    @Inject
    public TerminalPathUI(NodeRepository nodeRepository, PathView pathView, HomeUI homeUI, GraphUI graphUI, PathService pathService) {
        this.nodeRepository = nodeRepository;
        this.pathView = pathView;
        this.homeUI = homeUI;
        this.graphUI = graphUI;
        this.pathService = pathService;
    }

    private String[] inputTwoStartEndNodesNames() {
        String start;
        String end;

        System.out.println("Entrez le nom du noeud de départ :");
        start = SCANNER.nextLine();
        while (nodeRepository.getNode(start) == null) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            start = SCANNER.nextLine();
        }

        System.out.println("Entrez le nom du noeud d'arrivée :");
        end = SCANNER.nextLine();
        while (nodeRepository.getNode(end) == null) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            end = SCANNER.nextLine();
        }

        return new String[] {start, end};
    }

    @Override
    public void interact() {
        char choice;
        Path path;
        String[] inputsNodesNames;

        pathView.display();

        choice = SCANNER.nextLine().charAt(0);

        while(!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' -> {
                inputsNodesNames = inputTwoStartEndNodesNames();
                path = pathService.getShortestPath(inputsNodesNames[0], inputsNodesNames[1]);
                pathView.displayPath(path);
                this.interact();
            }
            default -> homeUI.interact();
        }

    }
}
