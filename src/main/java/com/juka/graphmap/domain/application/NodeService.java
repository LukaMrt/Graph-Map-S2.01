package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Node;
import com.juka.graphmap.domain.model.NodeType;

import java.util.ArrayList;
import java.util.List;

public class NodeService {

    private final NodeRepository nodeRepository;

    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getDirectNeighbours(String node) {
        return nodeRepository.getNode(node).getNeighbors();
    }

    public NodeType getType(String node) {
        return nodeRepository.getNode(node).getType();
    }
}
