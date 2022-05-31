package com.juka.graphmap.ui.neighbours.indirect;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.node.NodeDistanceService;
import com.juka.graphmap.domain.application.node.NodeService;

import javax.inject.Inject;

public class SwingIndirectNeighborsUI implements IndirectNeighborsUI {

    private final NodeDistanceService distanceService;
    private final NodeService nodeService;
    private final IndirectNeighborsView view;
    private final NodeRepository nodeRepository;

    @Inject
    public SwingIndirectNeighborsUI(IndirectNeighborsView view, NodeRepository nodeRepository, NodeDistanceService nodeDistanceService, NodeService nodeService) {
        this.view = view;
        this.nodeRepository = nodeRepository;
        this.distanceService = nodeDistanceService;
        this.nodeService = nodeService;
    }

    @Override
    public void interact(String node1, String node2) {

        nodeService.unSelectAll();

        if (node1 != null && !node1.isEmpty()) {
            nodeService.select(node1);
        }

        if (node2 != null && !node2.isEmpty()) {
            nodeService.select(node2);
        }

        boolean result = false;

        if (node1 != null && !node1.isEmpty() && node2 != null && !node2.isEmpty()) {
            result = distanceService.are2distance(node1, node2);
        }

        view.display(nodeRepository.getAllNodes(), node1, node2, result);
    }

}
