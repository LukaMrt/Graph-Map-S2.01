package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Node;

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

}
