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
        compareView.displayNodes(nodeRepository.getAllNodes());

        compareView.displayOptions();

        String[] inputs = new String[2];
        Node currentNode;
        Map<String, Boolean> comparaisonResult;
        char choice;

        for (int i = 0; i < inputs.length; i++) {
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
            }

        }

        comparaisonResult = nodeCompareService.nodeCompareCity(inputs[0], inputs[1]);

        compareView.displayComparaison(comparaisonResult, nodeRepository.getNode(inputs[0]),
                nodeRepository.getNode(inputs[1]));

        compareView.displayEndOptions();

        choice = SCANNER.nextLine().charAt(0);

        while (!"012".contains(String.valueOf(choice))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0 ou 1).");
            choice = SCANNER.nextLine().charAt(0);
        }

        if (choice == '0') {
            graphUI.interact();
            return;
        }

        homeUI.interact();
    }

}
