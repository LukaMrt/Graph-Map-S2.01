package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeCompareService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SwingCompareUI implements CompareUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final NodeCompareService nodeCompareService;
    private final CompareView view;

    @Inject
    public SwingCompareUI(GraphService graphService, NodeService nodeService, NodeCompareService nodeCompareService, CompareView view) {
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.nodeCompareService = nodeCompareService;
        this.view = view;
    }

    @Override
    public void interact() {

        List<Node> cities = graphService.getAllNodes().stream()
                .filter(node -> node.getType() == NodeType.CITY)
                .collect(Collectors.toList());

        view.display(cities);

    }

    @Override
    public void compare(String city1, String city2) {

        Map<String, Boolean> result = nodeCompareService.nodeCompareCity(city1, city2);

        view.displayComparaison(result, nodeService.getNode(city1), nodeService.getNode(city2));
    }

}
