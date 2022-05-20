package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SwingPathUI implements PathUI {

    private final GraphService graphService;
    private final PathService pathService;
    private final PathView view;

    @Inject
    public SwingPathUI(GraphService graphService, PathService pathService, PathView view) {
        this.graphService = graphService;
        this.pathService = pathService;
        this.view = view;
    }

    @Override
    public void interact(String nodeName1, String nodeName2) {

        List<String> nodes = graphService.getAllNodes().stream()
                .map(Node::getName)
                .toList();

        Path path = new Path(new ArrayList<>(), 0.0);

        if (nodeName1 != null && nodeName2 != null) {
            path = pathService.getShortestPath(nodeName1, nodeName2);
        }

        view.display(nodes, nodeName1, nodeName2, path);
    }

}
