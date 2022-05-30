package com.juka.graphmap.ui.home;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.roads.RoadsUI;

import javax.inject.Inject;
import java.util.Scanner;

public class TerminalHomeUI implements HomeUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final HomeView view;
    private final GraphUI graphUI;
    private final RoadsUI roadsUI;

    @Inject
    public TerminalHomeUI(GraphService graphService, HomeView view, GraphUI graphUI, RoadsUI roadsUI) {
        this.graphService = graphService;
        this.view = view;
        this.graphUI = graphUI;
        this.roadsUI = roadsUI;
    }

    @Override
    public void interact() {

        GraphCharacteristics graph = graphService.getGraphCharacteristics();
        view.display(graph, graphService.getAllNodes());

        char entry = SCANNER.nextLine().charAt(0);

        while (!"012".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1 ou 2).");
            entry = SCANNER.nextLine().charAt(0);
        }

        switch (entry) {
            case '1' -> graphUI.interact();
            case '2' -> roadsUI.interact(null, null, null);
            default -> System.out.println("Au revoir.");
        }

    }

}
