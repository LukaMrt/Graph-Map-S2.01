package com.juka.graphmap.ui.graph;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.Scanner;

public class TerminalGraphUI implements GraphUI {

    public static final Scanner scanner = new Scanner(System.in);

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;
    private final GraphView graphView;
    private final HomeUI homeUI;
    private final CompareUI compareUI;

    @Inject
    public TerminalGraphUI(NodeRepository nodeRepository, LinkRepository linkRepository, GraphView graphView, HomeUI homeUI, CompareUI compareUI) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
        this.graphView = graphView;
        this.homeUI = homeUI;
        this.compareUI = compareUI;
    }

    @Override
    public void interact() {
        char entry;

        graphView.displayMenu();

        entry = scanner.next().charAt(0);

        while (!"0123".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1, 2 ou 3).");
            entry = scanner.nextLine().charAt(0);
        }

        switch (entry) {
            case '0' -> homeUI.interact();
            case '1' -> graphView.displayNodes(nodeRepository.getAllNodes());
            case '2' -> graphView.displayLinks(linkRepository.getAllLinks());
            case '3' -> compareUI.interact();
        }

    }

}
