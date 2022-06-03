package com.juka.graphmap.ui.neighbours.indirect;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.node.NodeDistanceService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing implementation of the indirect neighbours UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingIndirectNeighborsUI implements IndirectNeighborsUI {

    private final NodeDistanceService distanceService;
    private final NodeService nodeService;
    private final IndirectNeighborsView view;
    private final NodeRepository nodeRepository;

    /**
     * Constructor of the SwingIndirectNeighborsUI.
     *
     * @param view                  the view of the indirect neighbours
     * @param nodeRepository        the node repository
     * @param nodeDistanceService   the node distance service
     * @param nodeService           the node service
     */
    @Inject
    public SwingIndirectNeighborsUI(IndirectNeighborsView view, NodeRepository nodeRepository, NodeDistanceService nodeDistanceService, NodeService nodeService) {
        this.view = view;
        this.nodeRepository = nodeRepository;
        this.distanceService = nodeDistanceService;
        this.nodeService = nodeService;
    }

    @Override
    public void interact(String node, int distance) {

        nodeService.unselectAll();

        if (node != null && !node.isEmpty()) {
            nodeService.select(node);
        }

        List<Node> result = new ArrayList<>();

        if (node != null && !node.isEmpty()) {
            result = distanceService.getNDistanceNeighbors(node, distance);
        }

        result.stream().map(Node::getName).forEach(nodeService::select);

        view.display(nodeRepository.getAllNodes(), node, distance, result);
    }

}
