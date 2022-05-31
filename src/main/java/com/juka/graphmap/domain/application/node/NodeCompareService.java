package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Service to compare two nodes.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeCompareService {

    private final NodeRepository nodeRepository;

    /**
     * Constructor of the NodeCompareService.
     *
     * @param nodeRepository NodeRepository to get Node characteristics.
     */
    @Inject
    public NodeCompareService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    /**
     * Returns the number of a specific type of node at 2 distance of a given node.
     *
     * @param node name of the given node.
     * @param nodeType type of node to count.
     * @return number of a specific type of node at 2 distance of a given node.
     */
    private int numberOfTypeAt2Distance(String node, NodeType nodeType) {

        Node currentNode = nodeRepository.getNode(node);

        int counter = 0;

        for (Node pass : currentNode.getNeighbors()) {
            for (Node twoDistanceNeighbor : pass.getNeighbors()) {
                if (twoDistanceNeighbor.getType() == nodeType) counter++;
            }
        }
        return counter;
    }

    /**
     * Returns a map of String and Boolean that contains the result of the comparison of two nodes.
     * The map contains the following keys:
     * - "ouverte" : true if the node1 is more open than node2
     * - "culturelle" : true if the node1 is more cultural than node2
     * - "gastronomique" : true if the node1 is more gastronomic than node2
     *
     * @param node1 name of the first node.
     * @param node2 name of the second node.
     * @return a map of String and Boolean that contains the result of the comparison of two nodes.
     */
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
