package com.juka.graphmap.ui.graph;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighboursUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighboursUI;
import com.juka.graphmap.ui.path.PathUI;

import java.util.Scanner;

public class TerminalGraphUI implements GraphUI {

    public static final Scanner scanner = new Scanner(System.in);

    private final NodeRepository nodeRepository;
    private final GraphView graphView;
    private final HomeUI homeUI;
    private final CompareUI compareUI;
    private final PathUI pathUI;
    private final DirectNeighboursUI directNeighboursUI;
    private final IndirectNeighboursUI indirectNeighboursUI;

    @Inject
    public TerminalGraphUI(NodeRepository nodeRepository, GraphView graphView, HomeUI homeUI, CompareUI compareUI, PathUI pathUI, DirectNeighboursUI directNeighboursUI, IndirectNeighboursUI indirectNeighboursUI) {
        this.nodeRepository = nodeRepository;
        this.graphView = graphView;
        this.homeUI = homeUI;
        this.compareUI = compareUI;
        this.pathUI = pathUI;
        this.directNeighboursUI = directNeighboursUI;
        this.indirectNeighboursUI = indirectNeighboursUI;
    }

    @Override
    public void interact() {
        char entry;

        graphView.display();

        entry = scanner.nextLine().charAt(0);

        while (!"0123456".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1, 2, 3, 4, 5 ou 6).");
            entry = scanner.nextLine().charAt(0);
        }

        switch (entry) {
            case '1' -> {
                graphView.displayNodes(nodeRepository);
                this.interact();
            }
            case '2' -> {
                graphView.displayLinks(nodeRepository);
                this.interact();
            }
            case '3' -> compareUI.interact();
            case '4' -> pathUI.interact();
            case '5' -> directNeighboursUI.interact();
            case '6' -> indirectNeighboursUI.interact();
            default -> homeUI.interact();
        }

    }

}
