package com.juka.graphmap.ui.graph;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;

import java.util.Scanner;

public class TerminalGraphUI implements GraphUI {

    public static final Scanner scanner = new Scanner(System.in);

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;
    private final GraphView graphView;
    private final GraphUI graphUI;

    @Inject
    public TerminalGraphUI(NodeRepository nodeRepository, LinkRepository linkRepository, GraphView graphView, GraphUI graphUI) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
        this.graphView = graphView;
        this.graphUI = graphUI;
    }

    @Override
    public void interact() {
        char entry;
        graphView.displayMenu();

        entry = scanner.next().charAt(0);

        while (!"012".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1 ou 2).");
            entry = scanner.nextLine().charAt(0);
        }

        switch (entry) {
            case '0' -> System.out.println("Au revoir.");
            case '1' -> graphView.displayNodes(nodeRepository.getAllNodes());
            case '2' -> graphView.displayLinks(linkRepository.getAllLinks());
        }
    }

}
