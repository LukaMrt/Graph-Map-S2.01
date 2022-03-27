package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathService {

    private final NodeRepository nodeRepository;

    public PathService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Path getShortestPath(String originNodeName, String destinationNodeName) {
        List<Node> path = new ArrayList<>();
        List<Node> remaining = new ArrayList<>(nodeRepository.getAllNodes());
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();
        Node minimalNode;
        Node currentNode;
        Node originNode = nodeRepository.getNode(originNodeName);
        Node destinationNode = nodeRepository.getNode(destinationNodeName);
        initializeDistances(distance, originNodeName);
        previous.put(originNode, null);

        while (!remaining.isEmpty()) {
            minimalNode = nodeWithMinimalDistance(distance, remaining);
            remaining.remove(minimalNode);
            for (Link link : minimalNode.getNeighborsLinks()) {
                release(distance, previous, minimalNode, link);
            }
        }

        if (distance.get(destinationNode) != Double.POSITIVE_INFINITY) {
            path.add(destinationNode);
            currentNode = destinationNode;
            while (previous.get(currentNode) != null) {
                path.add(0, previous.get(currentNode));
                currentNode = previous.get(currentNode);
            }
        }

        return new Path(path, distance.get(destinationNode));
    }

    private void initializeDistances(Map<Node, Double> distance, String originNodeName) {
        for (Node node : nodeRepository.getAllNodes()) {
            distance.put(node, Double.POSITIVE_INFINITY);
        }
        distance.put(nodeRepository.getNode(originNodeName), (double) 0);
    }

    private void release(Map<Node, Double> distance, Map<Node, Node> previous,
                         Node originNode, Link currentLink) {
        if (distance.get(currentLink.getDestination()) >
                (distance.get(originNode) + currentLink.getDistance())) {
            distance.put(currentLink.getDestination(), distance.get(originNode) + currentLink.getDistance());
            previous.put(currentLink.getDestination(), originNode);
        }
    }

    private Node nodeWithMinimalDistance(Map<Node, Double> distances, List<Node> remaining) {
        Node minimalNode = remaining.get(0);
        Double minimalDistance = distances.get(minimalNode);
        for (Node node : remaining) {
            if  (distances.get(node) < minimalDistance) {
                minimalNode = node;
                minimalDistance = distances.get(node);
            }
        }
        return minimalNode;
    }

}
