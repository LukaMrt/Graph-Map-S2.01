package com.juka.graphmap.ui.graph;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.path.PathUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalGraphUI implements GraphUI {

    public static final Scanner scanner = new Scanner(System.in);

    private final NodeRepository nodeRepository;
    private final GraphView graphView;
    private final HomeUI homeUI;
    private final CompareUI compareUI;
    private final PathUI pathUI;

    @Inject
    public TerminalGraphUI(NodeRepository nodeRepository, GraphView graphView, HomeUI homeUI, CompareUI compareUI, PathUI pathUI) {
        this.nodeRepository = nodeRepository;
        this.graphView = graphView;
        this.homeUI = homeUI;
        this.compareUI = compareUI;
        this.pathUI = pathUI;
    }

    @Override
    public void interact() {
        char entry;

        graphView.displayMenu();

        entry = scanner.nextLine().charAt(0);

        while (!"01234".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1, 2, 3 ou 4).");
            entry = scanner.nextLine().charAt(0);
        }

        switch (entry) {
            case '1' -> {
                graphView.displayNodes(nodeRepository.getAllNodes());
                this.interact();
            }
            case '2' -> {
                graphView.displayLinksHeader();
                for (Node node : nodeRepository.getAllNodes()) {
                    for (Link link : node.getNeighborsLinks()) {
                        graphView.displayLink(node, link);
                    }
                }
                this.interact();
            }
            case '3' -> compareUI.interact();
            case '4' -> pathUI.interact();
            default -> homeUI.interact();
        }

    }

}
