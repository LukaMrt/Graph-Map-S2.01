package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;

import javax.inject.Inject;
import java.util.List;

public class SwingDirectNeighborsUI implements DirectNeighborsUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final DirectNeighborsView view;

    @Inject
    public SwingDirectNeighborsUI(GraphService graphService, NodeService nodeService, LinkService linkService, DirectNeighborsView view) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
    }

    @Override
    public void interact(String node, String link) {

        NodeCharacteristics nodeCharacteristics = NodeCharacteristics.empty();

        if (node != null) {
            nodeCharacteristics = nodeService.getNodeCharacteristics(node);
        }

        LinkCharacteristics linkCharacteristics = LinkCharacteristics.empty();

        if (link != null) {
            linkCharacteristics = linkService.getLinkCharacteristics(link);
        }

        List<String> links = graphService.getAllLinks().stream()
                .map(Link::getRoadNameWithIndex)
                .distinct()
                .toList();

        List<String> nodes = graphService.getAllNodes().stream()
                .map(Node::getName)
                .toList();

        view.display(nodes, links, nodeCharacteristics, linkCharacteristics);

    }

}
