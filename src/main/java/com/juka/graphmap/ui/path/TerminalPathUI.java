package com.juka.graphmap.ui.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Terminal implementation of the path UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalPathUI implements PathUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final PathService pathService;
    private final GraphService graphService;
    private final NodeService nodeService;
    private final PathView pathView;
    private final HomeUI homeUI;
    private final GraphUI graphUI;

    /**
     * Constructor of the TerminalPathUI.
     *
     * @param pathView     the path view
     * @param homeUI       the home UI
     * @param graphUI      the graph UI
     * @param pathService  the path service
     * @param graphService the graph service
     * @param nodeService  the node service
     */
    @Inject
    public TerminalPathUI(PathView pathView, HomeUI homeUI, GraphUI graphUI, PathService pathService, GraphService graphService, NodeService nodeService) {
        this.graphService = graphService;
        this.pathView = pathView;
        this.homeUI = homeUI;
        this.graphUI = graphUI;
        this.pathService = pathService;
        this.nodeService = nodeService;
    }

    @Override
    public void interact(String nodeName1, String nodeName2) {

        Path path = new Path(new ArrayList<>(), 0.0);

        if (nodeName1 != null && nodeName2 != null) {
            path = pathService.getShortestPath(nodeName1, nodeName2);
        }

        pathView.display(graphService.getAllNodes(), nodeName1, nodeName2, path);

        char choice = SCANNER.nextLine().charAt(0);

        while(!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' -> this.interact(chooseLocation(1).getName(), chooseLocation(2).getName());
            default -> homeUI.interact();
        }

    }

    /**
     * Asks the user to choose a location.
     *
     * @param i the number of the location.
     * @return the chosen location.
     */
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
