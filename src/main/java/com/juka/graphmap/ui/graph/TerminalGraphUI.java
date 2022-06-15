package com.juka.graphmap.ui.graph;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.path.PathUI;

import java.util.Scanner;

/**
 * Terminal implementation of the Graph UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class TerminalGraphUI implements GraphUI {

    private static final Scanner scanner = new Scanner(System.in);

    private final GraphService graphService;
    private final GraphView graphView;
    private final HomeUI homeUI;
    private final CompareUI compareUI;
    private final PathUI pathUI;
    private final DirectNeighborsUI directNeighborsUI;
    private final IndirectNeighborsUI indirectNeighborsUI;

    /**
     * Constructor of the TerminalGraphUI.
     *
     * @param graphService        the graph service
     * @param graphView           the graph view
     * @param homeUI              the home UI
     * @param compareUI           the compare UI
     * @param pathUI              the path UI
     * @param directNeighborsUI   the direct neighbors UI
     * @param indirectNeighborsUI the indirect neighbors UI
     */
    @Inject
    public TerminalGraphUI(GraphService graphService, GraphView graphView, HomeUI homeUI, CompareUI compareUI, PathUI pathUI, DirectNeighborsUI directNeighborsUI, IndirectNeighborsUI indirectNeighborsUI) {
        this.graphService = graphService;
        this.graphView = graphView;
        this.homeUI = homeUI;
        this.compareUI = compareUI;
        this.pathUI = pathUI;
        this.directNeighborsUI = directNeighborsUI;
        this.indirectNeighborsUI = indirectNeighborsUI;
    }

    @Override
    public void interact() {
        char entry;

        graphView.display(graphService.getAllNodes(), graphService.getAllLinks());

        entry = scanner.nextLine().charAt(0);

        while (!"012345".contains(String.valueOf(entry))) {
            System.out.println("Entrée invalide. Veuillez réessayer (0, 1, 2, 3, 4, 5 ou 6).");
            entry = scanner.nextLine().charAt(0);
        }

        switch (entry) {
            case '1':
                compareUI.interact(null, null);
                break;
            case '2':
                pathUI.interact(null, null);
                break;
            case '3':
                directNeighborsUI.interact(null, null);
                break;
            case '4':
                indirectNeighborsUI.interact(null, 2);
                break;
            case '5':
                System.out.println("Au revoir.");
                break;
            default:
                homeUI.interact();
        }

    }

}
