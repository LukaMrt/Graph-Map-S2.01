package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.node.NodeCompareService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.comparaison.Comparaison;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing implementation for the compare UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingCompareUI implements CompareUI {

    private final GraphService graphService;
    private final NodeCompareService nodeCompareService;
    private final NodeService nodeService;
    private final CompareView view;

    /**
     * Constructor of the Swing compare UI.
     *
     * @param graphService          The graph service.
     * @param nodeCompareService    The node compare service.
     * @param nodeService           The node service.
     * @param view                  The view.
     */
    @Inject
    public SwingCompareUI(GraphService graphService, NodeCompareService nodeCompareService, NodeService nodeService, CompareView view) {
        this.graphService = graphService;
        this.nodeCompareService = nodeCompareService;
        this.nodeService = nodeService;
        this.view = view;
    }

    @Override
    public void interact(String city1, String city2) {

        List<Comparaison> result = new ArrayList<>();

        nodeService.unselectAll();

        if (city1 != null && city2 != null) {
            nodeCompareService.nodeCompareCity(city1, city2)
                    .entrySet().stream()
                    .map(entry -> new Comparaison(entry.getKey(), entry.getValue() ? city1 : city2, entry.getValue() ? city2 : city1))
                    .forEach(result::add);
        }

        if (city1 != null) {
            nodeService.select(city1);
        }

        if (city2 != null) {
            nodeService.select(city2);
        }

        List<String> cities = graphService.getAllNodes().stream()
                .filter(node -> node.getType() == NodeType.CITY)
                .map(Node::getName)
                .toList();

        view.display(graphService.getAllNodes(), cities, result, city1, city2);

    }

}
