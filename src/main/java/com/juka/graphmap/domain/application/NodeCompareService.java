package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Node;
import com.juka.graphmap.domain.model.NodeType;

import java.util.HashMap;
import java.util.Map;

public class NodeCompareService {

    private final NodeRepository nodeRepository;

    public NodeCompareService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    private int numberOfTypeAt2Distance(String node, NodeType nodeType) {
        Node currentNode = nodeRepository.getNode(node);
        assert (currentNode.getType() == NodeType.CITY) : node + " is not a City";
        int counter = 0;

        for (Node pass : currentNode.getNeighbors()) {
            for (Node twoDistanceNeighbor : pass.getNeighbors()) {
                if (twoDistanceNeighbor.getType() == nodeType) counter++;
            }
        }
        return counter;
    }

    public Map<String, Boolean> nodeCompareCity(String node1, String node2) {
        Map<String, Boolean> isMore = new HashMap<>();
        isMore.put("open", false);
        isMore.put("cultural", false);
        isMore.put("gastronomic", false);

        if (numberOfTypeAt2Distance(node1, NodeType.CITY) > numberOfTypeAt2Distance(node2, NodeType.CITY))
            isMore.put("open", true);
        if (numberOfTypeAt2Distance(node1, NodeType.RECREATION_CENTER) > numberOfTypeAt2Distance(node2, NodeType.RECREATION_CENTER))
            isMore.put("cultural", true);
        if (numberOfTypeAt2Distance(node1, NodeType.RESTAURANT) > numberOfTypeAt2Distance(node2, NodeType.RESTAURANT))
            isMore.put("gastronomic", true);

        return isMore;
    }
}
