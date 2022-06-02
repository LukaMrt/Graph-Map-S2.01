package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Service to check if two nodes are at 2 distance.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeDistanceService {

    private final NodeRepository nodeRepository;

    /**
     * Constructor of the NodeDistanceService.
     *
     * @param nodeRepository NodeRepository of the graph.
     */
    @Inject
    public NodeDistanceService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    /**
     * Returns true if the node1 is at 2 distance of the node2.
     *
     * @param node1 name of the first node.
     * @param node2 name of the second node.
     * @param n     distance.
     * @return true if the node1 is at 2 distance of the node2.
     */
    public boolean areNDistance(String node1, String node2, int n) {

        Stream<Node> stream = nodeRepository.getNode(node1).getNeighbors().stream();

        for (int i = 0; i < n - 1; i++) {
            stream = stream.map(Node::getNeighbors).flatMap(Collection::stream).distinct();
        }

        return stream.map(Node::getName).anyMatch(node2::equals);
    }

}
