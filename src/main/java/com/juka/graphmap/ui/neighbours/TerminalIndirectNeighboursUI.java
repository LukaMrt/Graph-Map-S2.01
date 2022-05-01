package com.juka.graphmap.ui.neighbours;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeDistanceService;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.Scanner;

public class TerminalIndirectNeighboursUI implements IndirectNeighboursUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final NodeDistanceService nodeDistanceService;
    private final GraphUI graphUI;
    private final HomeUI homeUI;
    private final IndirectNeighboursView view;

    @Inject
    public TerminalIndirectNeighboursUI(GraphService graphService, NodeDistanceService nodeDistanceService, GraphUI graphUI, HomeUI homeUI, IndirectNeighboursView view) {
        this.graphService = graphService;
        this.nodeDistanceService = nodeDistanceService;
        this.graphUI = graphUI;
        this.homeUI = homeUI;
        this.view = view;
    }

    @Override
    public void interact() {

        view.display();

        char choice = SCANNER.nextLine().charAt(0);

        if (!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' -> {
                String[] inputs = inputTwoNodesName();
                boolean result = nodeDistanceService.are2distance(inputs[0], inputs[1]);
                view.displayResult(inputs[0], inputs[1], result);
                this.interact();
            }
            case '2' -> homeUI.interact();
        }

    }

    private String[] inputTwoNodesName() {

        String[] inputs = new String[2];
        view.displayNodes(graphService.getAllNodes());

        for (int i = 0; i < 2; i++) {
            System.out.println();
            System.out.println("Entrez la ville n°" + (i + 1) + " :");
            inputs[i] = SCANNER.nextLine();
            while (!graphService.nodeExist(inputs[i])) {
                System.out.println("Entrée invalide. Veuillez réessayer.");
                inputs[i] = SCANNER.nextLine();
            }
        }

        return inputs;
    }

}
