package com.juka.graphmap.ui.compare;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.node.NodeCompareService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeUI;

import java.util.Map;
import java.util.Scanner;

public class TerminalCompareUI implements CompareUI {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final NodeCompareService nodeCompareService;
    private final NodeRepository nodeRepository;
    private final CompareView compareView;
    private final HomeUI homeUI;
    private final GraphUI graphUI;

    @Inject
    public TerminalCompareUI(NodeCompareService nodeCompareService, NodeRepository nodeRepository,
                             CompareView compareView, HomeUI homeUI, GraphUI graphUI) {
        this.nodeCompareService = nodeCompareService;
        this.nodeRepository = nodeRepository;
        this.compareView = compareView;
        this.homeUI = homeUI;
        this.graphUI = graphUI;
    }

    @Override
    public void interact() {
        Map<String, Boolean> comparaisonResult;
        char choice;

        compareView.display(nodeRepository.getAllNodes());

        choice = SCANNER.nextLine().charAt(0);

        if (!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer.");
            choice = SCANNER.nextLine().charAt(0);
        }

        switch (choice) {
            case '0' -> graphUI.interact();
            case '1' -> {
                String[] inputs = inputTwoNodesName();
                comparaisonResult = nodeCompareService.nodeCompareCity(inputs[0], inputs[1]);
                compareView.displayComparaison(comparaisonResult, nodeRepository.getNode(inputs[0]),
                        nodeRepository.getNode(inputs[1]));
                this.interact();
            }
            default -> homeUI.interact();
        }
    }

    @Override
    public void compare(String city1, String city2) {

    }

    private String[] inputTwoNodesName() {
        String[] inputs = new String[2];
        Node currentNode;

        compareView.displayNodes(nodeRepository);

        for (int i = 0; i < 2; i++) {
            System.out.println();
            System.out.println("Entrez la ville n°" + (i + 1) + " :");
            inputs[i] = SCANNER.nextLine();
            currentNode = nodeRepository.getNode(inputs[i]);
            while (currentNode == null) {
                System.out.println("Entrée invalide. Veuillez réessayer.");
                inputs[i] = SCANNER.nextLine();
                currentNode = nodeRepository.getNode(inputs[i]);
            }
            while (currentNode.getType() != NodeType.CITY) {
                System.out.println("Noeud " + currentNode.getName() + " invalide. Choisissez une ville.");
                inputs[i] = SCANNER.nextLine();
                currentNode = nodeRepository.getNode(inputs[i]);
                while (currentNode == null) {
                    System.out.println("Entrée invalide. Veuillez réessayer.");
                    inputs[i] = SCANNER.nextLine();
                    currentNode = nodeRepository.getNode(inputs[i]);
                }
            }
        }
        return inputs;
    }

}
