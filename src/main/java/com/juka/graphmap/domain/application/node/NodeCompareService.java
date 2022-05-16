package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class NodeCompareService {

    private final NodeRepository nodeRepository;

    @Inject
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
        isMore.put("ouverte", false);
        isMore.put("culturelle", false);
        isMore.put("gastronomique", false);

        if (numberOfTypeAt2Distance(node1, NodeType.CITY) > numberOfTypeAt2Distance(node2, NodeType.CITY))
            isMore.put("ouverte", true);
        if (numberOfTypeAt2Distance(node1, NodeType.RECREATION_CENTER) > numberOfTypeAt2Distance(node2, NodeType.RECREATION_CENTER))
            isMore.put("culturelle", true);
        if (numberOfTypeAt2Distance(node1, NodeType.RESTAURANT) > numberOfTypeAt2Distance(node2, NodeType.RESTAURANT))
            isMore.put("gastronomique", true);

        return isMore;
    }
}
