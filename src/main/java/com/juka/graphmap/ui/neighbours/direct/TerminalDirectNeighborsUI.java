package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.ui.graph.GraphUI;

import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

/**
 * Terminal implementation for the DirectNeighborsUI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalDirectNeighborsUI implements DirectNeighborsUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final DirectNeighborsView view;
    private final GraphUI graphUI;

    /**
     * Constructor of the TerminalDirectNeighborsUI.
     *
     * @param graphService The graph service
     * @param nodeService  The node service
     * @param linkService  The link service
     * @param view         The view
     * @param graphUI      The graph UI
     */
    @Inject
    public TerminalDirectNeighborsUI(GraphService graphService, NodeService nodeService, LinkService linkService, DirectNeighborsView view, GraphUI graphUI) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
        this.graphUI = graphUI;
    }

    @Override
    public void interact(String node, String link) {

        LinkCharacteristics characteristics = link != null && !link.isEmpty() ? linkService.getLinkCharacteristics(linkService.getLink(link).getRoadNameWithIndex()) : LinkCharacteristics.empty();
        NodeCharacteristics characteristics2 = node != null && !node.isEmpty() ? nodeService.getNodeCharacteristics(nodeService.getNode(node).getName()) : NodeCharacteristics.empty();
        List<String> links = graphService.getAllLinks().stream().map(Link::getRoadNameWithIndex).distinct().toList();

        view.display(graphService.getAllNodes(), links, characteristics2, characteristics);

        char choice = SCANNER.nextLine().charAt(0);

        if (!"0123".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> System.out.println("Au revoir");
            case '1' -> this.interact(chooseLocation(), link);
            case '2' -> this.interact(node, chooseLink());
            default -> graphUI.interact();
        }

    }

    /**
     * Asks the user to choose a location.
     *
     * @return The name of the node chosen by the user
     */
    private String chooseLocation() {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return entry;
    }

    /**
     * Asks the user to choose a link.
     *
     * @return The name of the link chosen by the user
     */
    private String chooseLink() {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le lien à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.linkExist(entry));

        return entry;
    }

}
