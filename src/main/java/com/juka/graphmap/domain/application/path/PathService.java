package com.juka.graphmap.domain.application.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.road.Road;
import com.juka.graphmap.domain.model.step.Step;

import java.util.*;

public class PathService implements RoadsFinderService {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;

    @Inject
    public PathService(NodeRepository nodeRepository, LinkRepository linkRepository) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
    }

    public Path getShortestPath(String originNodeName, String destinationNodeName) {
        Map<Node, Double> distances = new HashMap<>();
        Map<Step, Node> previous = new HashMap<>();
        Map<Node, Step> matchingSteps = new HashMap<>();
        Step tmp;

        nodeRepository.getAllNodes().forEach(node -> distances.put(node, Double.POSITIVE_INFINITY));
        distances.put(nodeRepository.getNode(originNodeName), 0.0);
        tmp = new Step(nodeRepository.getNode(originNodeName), null);
        previous.put(tmp, null);
        matchingSteps.put(nodeRepository.getNode(originNodeName), tmp);

        List<Node> remaining = new ArrayList<>(nodeRepository.getAllNodes());
        while (!remaining.isEmpty()) {
            remaining.sort(Comparator.comparing(distances::get));
            Node minimalNode = remaining.get(0);
            remaining.remove(minimalNode);
            executeDijkstra(distances, previous, minimalNode, matchingSteps);
        }

        return buildPath(distances, nodeRepository.getNode(destinationNodeName), previous, matchingSteps);
    }

    private void executeDijkstra(Map<Node, Double> distances, Map<Step, Node> previous, Node minimalNode,
                                 Map<Node, Step> stepMap) {
        Double currentDistance = distances.get(minimalNode);
        final Step[] tmp = new Step[1];
        minimalNode.getNeighborsLinks().stream()
                .filter(link -> distances.get(link.getDestination()) > currentDistance + link.getDistance())
                .forEach(link -> {
                    distances.put(link.getDestination(), currentDistance + link.getDistance());
                    tmp[0] = new Step(link.getDestination(), link);
                    previous.put(tmp[0], minimalNode);
                    stepMap.put(link.getDestination(), tmp[0]);
                });
    }

    private Path buildPath(Map<Node, Double> distances, Node destinationNode, Map<Step, Node> previous,
                           Map<Node, Step> stepMap) {

        if (distances.get(destinationNode) == Double.POSITIVE_INFINITY) {
            return new Path(new ArrayList<>(), Double.POSITIVE_INFINITY);
        }

        List<Step> path = new ArrayList<>();

        path.add(stepMap.get(destinationNode));

        Step currentStep = stepMap.get(destinationNode);

        while ((currentStep = stepMap.get(previous.get(currentStep))) != null) {
            path.add(0, currentStep);
        }

        return new Path(path, distances.get(destinationNode));
    }

    @Override
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
