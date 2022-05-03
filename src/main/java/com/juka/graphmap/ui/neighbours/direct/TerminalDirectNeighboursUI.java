package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

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
    private final HomeUI homeUI;

    @Inject
    public TerminalDirectNeighboursUI(GraphService graphService, NodeService nodeService, LinkService linkService, DirectNeighboursView view, GraphUI graphUI, HomeUI homeUI) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
        this.graphUI = graphUI;
        this.homeUI = homeUI;
    }

    @Override
    public void interact() {

        view.display();

        char choice = SCANNER.nextLine().charAt(0);

        if (!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' -> {
                String node = chooseLocation();
                List<Node> neighbours = nodeService.getDirectNeighbours(node);
                view.displayNeighbours(neighbours, node);
                this.interact();
            }
            case '2' -> {
                String link = chooseLink();
                LinkCharacteristics characteristics = linkService.getLinkCharacteristics(link);
                view.displayLinkCharacteristics(characteristics);
                this.interact();
            }
            default -> homeUI.interact();
        }

    }

    private String chooseLocation() {

        List<Node> nodes = graphService.getAllNodes();
        view.displayNodes(nodes);

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return entry;
    }

    private String chooseLink() {

        List<String> links = graphService.getAllLinks().stream()
                .map(Link::getRoadNameWithIndex)
                .distinct()
                .sorted()
                .toList();
        view.displayLinks(links);

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le lien à étudier :");
            entry = SCANNER.nextLine();
        } while (!linkService.linkExist(entry));

        return entry;
    }

}
