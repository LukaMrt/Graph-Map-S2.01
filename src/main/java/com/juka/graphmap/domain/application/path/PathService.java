package com.juka.graphmap.domain.application.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.road.Road;

import java.util.*;

public class PathService {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;

    public PathService(NodeRepository nodeRepository, LinkRepository linkRepository) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
    }

    public Path getShortestPath(String originNodeName, String destinationNodeName) {
        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> previous = new HashMap<>();

        nodeRepository.getAllNodes().forEach(node -> distances.put(node, Double.POSITIVE_INFINITY));
        distances.put(nodeRepository.getNode(originNodeName), 0.0);
        previous.put(nodeRepository.getNode(originNodeName), null);

        List<Node> remaining = new ArrayList<>(nodeRepository.getAllNodes());
        while (!remaining.isEmpty()) {
            remaining.sort(Comparator.comparing(distances::get));
            Node minimalNode = remaining.get(0);
            remaining.remove(minimalNode);
            executeDijkstra(distances, previous, minimalNode);
        }

        return buildPath(distances, nodeRepository.getNode(destinationNodeName), previous);
    }

    private void executeDijkstra(Map<Node, Double> distances, Map<Node, Node> previous, Node minimalNode) {
        Double currentDistance = distances.get(minimalNode);

        minimalNode.getNeighborsLinks().stream()
                .filter(link -> distances.get(link.getDestination()) > currentDistance + link.getDistance())
                .forEach(link -> {
                    distances.put(link.getDestination(), currentDistance + link.getDistance());
                    previous.put(link.getDestination(), minimalNode);
                });
    }

    private Path buildPath(Map<Node, Double> distances, Node destinationNode, Map<Node, Node> previous) {

        if (distances.get(destinationNode) == Double.POSITIVE_INFINITY) {
            return new Path(new ArrayList<>(), Double.POSITIVE_INFINITY);
        }

        List<Node> path = new ArrayList<>();
        path.add(destinationNode);
        Node currentNode = destinationNode;

        while ((currentNode = previous.get(currentNode)) != null) {
            path.add(0, currentNode);
        }

        return new Path(path, distances.get(destinationNode));
    }

    public List<Road> getPathsWithSpecificLocations(int cityCount, int restaurantCount, int recreationCenterCount) {

        return linkRepository.getAllLinks().stream()
                .map(Link::getRoadName)
                .distinct()
                .map(road -> new Road(road, nodeRepository.getAllNodes()
                        .stream()
                        .filter(node -> node.getNeighborsLinks().stream()
                                .map(Link::getRoadName)
                                .anyMatch(roadName -> roadName.equals(road)))
                        .toList()))
                .filter(road -> cityCount <= road.countCities())
                .filter(road -> restaurantCount <= road.countRestaurants())
                .filter(road -> recreationCenterCount <= road.countRecreationCenters())
                .toList();
    }

}
