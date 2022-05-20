package com.juka.graphmap.ui.neighbours.indirect;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.node.NodeDistanceService;

import javax.inject.Inject;

public class SwingIndirectNeighborsUI implements IndirectNeighborsUI {

    private final IndirectNeighborsView view;
    private final NodeRepository nodeRepository;
    private final NodeDistanceService distanceService;

    @Inject
    public SwingIndirectNeighborsUI(IndirectNeighborsView view, NodeRepository nodeRepository, NodeDistanceService nodeDistanceService) {
        this.view = view;
        this.nodeRepository = nodeRepository;
        this.distanceService = nodeDistanceService;
    }

    @Override
    public void interact(String node1, String node2) {
        if (node1 == null && node2 == null) {
            view.display(nodeRepository.getAllNodes(), null, null, false);
        } else {
            view.display(nodeRepository.getAllNodes(), node1, node2, distanceService.are2distance(node1, node2));
        }
    }

}
