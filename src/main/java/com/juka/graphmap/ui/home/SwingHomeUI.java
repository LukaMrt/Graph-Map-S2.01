package com.juka.graphmap.ui.home;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.graph.GraphCharacteristics;

import javax.inject.Inject;

/**
 * Swing implementation for the home UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingHomeUI implements HomeUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final HomeView view;

    /**
     * Constructor of the SwingHomeUI.
     *
     * @param graphService the graph service
     * @param nodeService  the node service
     * @param linkService  the link service
     * @param view         the home view
     */
    @Inject
    public SwingHomeUI(GraphService graphService, NodeService nodeService, LinkService linkService, HomeView view) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
    }

    @Override
    public void interact() {

        nodeService.unselectAll();
        linkService.unselectAll();

        GraphCharacteristics characteristics = graphService.getGraphCharacteristics();
        view.display(characteristics, graphService.getAllNodes());

    }

}
