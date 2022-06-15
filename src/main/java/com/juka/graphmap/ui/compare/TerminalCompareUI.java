package com.juka.graphmap.ui.compare;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeCompareService;
import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Terminal implementation for the compare UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalCompareUI implements CompareUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final NodeCompareService nodeCompareService;
    private final GraphService graphService;
    private final CompareView compareView;
    private final HomeUI homeUI;
    private final GraphUI graphUI;

    /**
     * The constructor of the terminal compare UI.
     *
     * @param nodeCompareService The node compare service.
     * @param graphService       The graph service.
     * @param compareView        The compare view.
     * @param homeUI             The home UI.
     * @param graphUI            The graph UI.
     */
    @Inject
    public TerminalCompareUI(NodeCompareService nodeCompareService, GraphService graphService, CompareView compareView, HomeUI homeUI, GraphUI graphUI) {
        this.nodeCompareService = nodeCompareService;
        this.graphService = graphService;
        this.compareView = compareView;
        this.homeUI = homeUI;
        this.graphUI = graphUI;
    }

    @Override
    public void interact(String city1, String city2) {

        List<Comparaison> result = new ArrayList<>();

        if (city1 != null && city2 != null) {
            nodeCompareService.nodeCompareCity(city1, city2)
                    .entrySet().stream()
                    .map(entry -> new Comparaison(entry.getKey(), entry.getValue() ? city1 : city2, entry.getValue() ? city2 : city1))
                    .forEach(result::add);
        }

        List<String> cities = graphService.getAllNodes().stream()
                .filter(node -> node.getType() == NodeType.CITY)
                .map(Node::getName)
                .collect(Collectors.toList());

        compareView.display(graphService.getAllNodes(), cities, result, city1, city2);

        char choice = SCANNER.nextLine().charAt(0);

        if (!"0123".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0':
                graphUI.interact();
                break;
            case '1':
                this.interact(chooseLocation(1), chooseLocation(2));
                break;
            default:
                homeUI.interact();
        }
    }

    /**
     * Asks the user to choose a location.
     *
     * @param i The number of the node. (1 or 2)
     * @return The name of the location.
     */
    private String chooseLocation(int i) {

        System.out.println();
        String entry;
        do {
            System.out.println("Entrez le noeud n°" + i + " à étudier :");
            entry = SCANNER.nextLine();
        } while (!graphService.nodeExist(entry));

        return entry;
    }

}
