package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.List;

public class SwingGraphUI implements GraphUI {

    private final GraphService graphService;
    private final GraphView view;

    @Inject
    public SwingGraphUI(GraphService graphService, GraphView view) {
        this.graphService = graphService;
        this.view = view;
    }

    @Override
    public void interact() {

        List<String> nodes = graphService.getAllNodes().stream()
                .map(Node::getName)
                .toList();

        List<String> links = graphService.getAllLinks().stream()
                .map(Link::getRoadNameWithIndex)
                .toList();

        view.display(nodes, links);
    }

}
