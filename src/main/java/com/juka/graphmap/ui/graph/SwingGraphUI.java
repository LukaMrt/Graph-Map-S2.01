package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.link.Link;

import javax.inject.Inject;
import java.util.List;

public class SwingGraphUI implements GraphUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final GraphView view;

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
                .map(Link::getRoadNameWithIndex)
                .toList();

        nodeService.unselectAll();
        linkService.unselectAll();

        view.display(graphService.getAllNodes(), links);
    }

}
