package com.juka.graphmap.ui.neighbours.indirect;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeDistanceService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.Scanner;

public class TerminalIndirectNeighboursUI implements IndirectNeighboursUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final NodeDistanceService nodeDistanceService;
    private final NodeService nodeService;
    private final GraphUI graphUI;
    private final HomeUI homeUI;
    private final IndirectNeighboursView view;

    @Inject
    public TerminalIndirectNeighboursUI(GraphService graphService, NodeDistanceService nodeDistanceService, NodeService nodeService, GraphUI graphUI, HomeUI homeUI, IndirectNeighboursView view) {
        this.graphService = graphService;
        this.nodeDistanceService = nodeDistanceService;
        this.nodeService = nodeService;
        this.graphUI = graphUI;
        this.homeUI = homeUI;
        this.view = view;
    }

    @Override
    public void interact(Node node1, Node node2) {

        boolean result = false;
        String nodeName1 = null;
        String nodeName2 = null;

        if (node1 != null && node2 != null) {
            nodeName1 = node1.getName();
            nodeName2 = node2.getName();
            result = nodeDistanceService.are2distance(nodeName1, nodeName2);
        }

        view.displayNodes(graphService.getAllNodes(), nodeName1, nodeName2, result);

        char choice = SCANNER.nextLine().charAt(0);

        if (!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' -> this.interact(chooseLocation(1), chooseLocation(2));
            default -> homeUI.interact();
        }

    }

    private Node chooseLocation(int i) {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud n°" + i + " à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return nodeService.getNode(entry);
    }

}
