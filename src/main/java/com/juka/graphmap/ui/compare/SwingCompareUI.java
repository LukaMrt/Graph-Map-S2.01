package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeCompareService;
import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SwingCompareUI implements CompareUI {

    private final GraphService graphService;
    private final NodeCompareService nodeCompareService;
    private final CompareView view;

    @Inject
    public SwingCompareUI(GraphService graphService, NodeCompareService nodeCompareService, CompareView view) {
        this.graphService = graphService;
        this.nodeCompareService = nodeCompareService;
        this.view = view;
    }

    @Override
    public void interact(String city1, String city2) {

        List<Comparaison> result = new ArrayList<>();

        if (city1 != null && city2 != null) {
            nodeCompareService.nodeCompareCity(city1, city2)
                    .entrySet().stream()
                    .map(entry -> new Comparaison(entry.getKey(), entry.getValue() ? city1 : city2, entry.getValue() ? city2 : city1))
                    .forEach(result::add);
        }

        List<String> nodes = graphService.getAllNodes().stream()
                .filter(node -> node.getType() == NodeType.CITY)
                .map(Node::getName)
                .toList();

        view.display(nodes, result);

    }

}
