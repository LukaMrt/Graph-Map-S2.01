package com.juka.graphmap.ui.neighbours.indirect;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeDistanceService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Terminal implementation of the Indirect neighbors UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalIndirectNeighborsUI implements IndirectNeighborsUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final NodeDistanceService nodeDistanceService;
    private final NodeService nodeService;
    private final GraphUI graphUI;
    private final HomeUI homeUI;
    private final IndirectNeighborsView view;

    /**
     * Constructor.
     *
     * @param graphService        Graph service
     * @param nodeDistanceService Node distance service
     * @param nodeService         Node service
     * @param graphUI             Graph UI
     * @param homeUI              Home UI
     * @param view                Indirect neighbors view
     */
    @Inject
    public TerminalIndirectNeighborsUI(GraphService graphService, NodeDistanceService nodeDistanceService, NodeService nodeService, GraphUI graphUI, HomeUI homeUI, IndirectNeighborsView view) {
        this.graphService = graphService;
        this.nodeDistanceService = nodeDistanceService;
        this.nodeService = nodeService;
        this.graphUI = graphUI;
        this.homeUI = homeUI;
        this.view = view;
    }

    @Override
    public void interact(String node, int distance) {

        List<Node> result = new ArrayList<>();

        if (node != null) {
            result = nodeDistanceService.getNDistanceNeighbors(node, distance);
        }

        view.display(graphService.getAllNodes(), node, distance, result);

        char choice = SCANNER.nextLine().charAt(0);

        if (!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' ->
                    this.interact(chooseLocation().getName(), chooseDistance());
            default -> homeUI.interact();
        }

    }

    private int chooseDistance() {

        System.out.println();
        String entry;

        do {
            System.out.println("Entrez la distance :");
            entry = SCANNER.nextLine();
        } while (!"1 2 3 4 5 6 7 8 9 10".contains(entry));

        return Integer.parseInt(entry);
    }

    private Node chooseLocation() {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return nodeService.getNode(entry);
    }

}
