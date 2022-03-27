package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;

import java.util.Collection;

public class NodeDistanceService {

    private final NodeRepository nodeRepository;

    public NodeDistanceService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public boolean are2distance(String node1, String node2) {

        return nodeRepository.getNode(node1)
                .getNeighbors()
                .stream()
                .map(Node::getNeighbors)
                .flatMap(Collection::stream)
                .map(Node::getName)
                .anyMatch(node2::equals);
    }

}
