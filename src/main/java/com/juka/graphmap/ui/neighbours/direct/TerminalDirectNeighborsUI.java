package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.ui.graph.GraphUI;

import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

public class TerminalDirectNeighborsUI implements DirectNeighborsUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final DirectNeighborsView view;
    private final GraphUI graphUI;

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

        // TODO : review conditions
        LinkCharacteristics characteristics = link != null && !link.isEmpty() ? linkService.getLinkCharacteristics(linkService.getLink(link).getRoadNameWithIndex()) : LinkCharacteristics.empty();
        NodeCharacteristics characteristics2 = node != null && !node.isEmpty() ? nodeService.getNodeCharacteristics(nodeService.getNode(node).getName()) : NodeCharacteristics.empty();
        List<String> links = graphService.getAllLinks().stream().map(Link::getRoadNameWithIndex).distinct().toList();
        List<String> nodes = graphService.getAllNodes().stream().map(Node::getName).toList();

        view.display(nodes, links, characteristics2, characteristics);

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

    private String chooseLocation() {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return entry;
    }

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
