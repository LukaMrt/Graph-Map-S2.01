package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

/**
 * Swing implementation of the Graph UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingGraphUI implements GraphUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final GraphView view;


    /**
     * Constructor of the SwingGraphUI.
     *
     * @param graphService the graph service
     * @param nodeService  the node service
     * @param linkService  the link service
     * @param view         the view
     */
    @Inject
    public SwingGraphUI(GraphService graphService, NodeService nodeService, LinkService linkService, GraphView view) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
    }

    @Override
    public void interact() {

        List<String> links = graphService.getAllLinks().stream()
                .sorted(Comparator.comparing(Link::getType))
                .map(Link::getRoadNameWithIndex)
                .toList();

        nodeService.unselectAll();
        linkService.unselectAll();

        List<Node> nodes = graphService.getAllNodes().stream()
                .sorted(Comparator.comparing(Node::getType))
                .toList();

        view.display(nodes, links);
    }

}
