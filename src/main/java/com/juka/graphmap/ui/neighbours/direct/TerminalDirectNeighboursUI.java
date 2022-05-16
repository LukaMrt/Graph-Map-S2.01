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

public class TerminalDirectNeighboursUI implements DirectNeighboursUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final DirectNeighboursView view;
    private final GraphUI graphUI;

    @Inject
    public TerminalDirectNeighboursUI(GraphService graphService, NodeService nodeService, LinkService linkService, DirectNeighboursView view, GraphUI graphUI) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
        this.graphUI = graphUI;
    }

    @Override
    public void interact(Node node, Link link) {

        LinkCharacteristics characteristics = link != null ? linkService.getLinkCharacteristics(link.getRoadNameWithIndex()) : null;
        NodeCharacteristics characteristics2 = node != null ? nodeService.getNodeCharacteristics(node.getName()) : null;
        List<String> links = graphService.getAllLinks().stream().map(Link::getRoadNameWithIndex).toList();

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

    private Node chooseLocation() {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return nodeService.getNode(entry);
    }

    private Link chooseLink() {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le lien à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.linkExist(entry));

        return linkService.getLink(entry);
    }

}
