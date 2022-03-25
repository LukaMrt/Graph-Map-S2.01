package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Link;
import com.juka.graphmap.domain.model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathService {
    private final NodeRepository nodeRepository;

    public ShortestPathService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    private void initializeDistances(Map<String, Double> distance, String originNodeName) {
        for (Node node : nodeRepository.getAllNodes()) {
            distance.put(node.getName(), Double.POSITIVE_INFINITY);
        }
        distance.put(originNodeName, (double) 0);
    }

    private void release(Map<String, Double> distance, Map<Node, Node> previous,
                         Node originNode, Link currentLink) {
        if (distance.get(currentLink.getDestination().getName()) >
                (distance.get(originNode.getName()) + currentLink.getDistance())) {
            distance.put(currentLink.getDestination().getName(), distance.get(originNode.getName()) + currentLink.getDistance());
            previous.put(currentLink.getDestination(), originNode);
        }
    }

    public Node nodeWithMinimalDistance(Map<String, Double> distances, List<Node> remaining) {
        Node minimalNode = remaining.get(0);
        Double minimalDistance = distances.get(minimalNode.getName());
        for (Node node : remaining) {
            if  (distances.get(node.getName()) < minimalDistance) {
                minimalNode = node;
                minimalDistance = distances.get(node.getName());
            }
        }
        return minimalNode;
    }

    public ShortestPath getShortestPath(String originNodeName, String destinationNodeName) {
        List<Node> path = new ArrayList<>();
        List<Node> remaining = new ArrayList<>(nodeRepository.getAllNodes());
        Map<String, Double> distance = new HashMap<>();
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

        if (distance.get(destinationNodeName) != Double.POSITIVE_INFINITY) {
            path.add(destinationNode);
            currentNode = destinationNode;
            while (previous.get(currentNode) != null) {
                path.add(0, previous.get(currentNode));
                currentNode = previous.get(currentNode);
            }
        }

        return new ShortestPath(path, distance.get(destinationNodeName));
    }
}
