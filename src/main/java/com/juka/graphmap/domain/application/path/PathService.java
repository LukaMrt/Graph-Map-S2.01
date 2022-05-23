package com.juka.graphmap.domain.application.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.FloydWarshallStep;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.road.Road;
import com.juka.graphmap.domain.model.path.Step;

import java.util.*;

public class PathService implements RoadsFinderService {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;
    private final FloydWarshallDistancesRepository distanceRepository;

    @Inject
    public PathService(NodeRepository nodeRepository, LinkRepository linkRepository, FloydWarshallDistancesRepository distanceRepository) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
        this.distanceRepository = distanceRepository;
    }

    public Path getShortestPath(String originNodeName, String destinationNodeName) {

        Node node = nodeRepository.getNode(originNodeName);
        Node destination = nodeRepository.getNode(destinationNodeName);

        List<Node> nodes = nodeRepository.getAllNodes().stream().sorted().toList();

        FloydWarshallStep[] distances = distanceRepository.getDistances(nodes.indexOf(node));

        int distance = distances[nodes.indexOf(node)].distance;
        List<Step> steps = new ArrayList<>();

        Node currentNode = destination;

        while (currentNode != node) {
            System.out.println(currentNode.getName());
            int currentNodeIndex = nodes.indexOf(currentNode);
            int previousNodeIndex = nodes.indexOf(distances[currentNodeIndex].previous);
            Link previousLink = distances[currentNodeIndex].previousLink;

            distance += previousLink.getDistance();
            steps.add(new Step(currentNode, previousLink));

            currentNode = nodes.get(previousNodeIndex);
        }

        steps.add(new Step(node, null));
        Collections.reverse(steps);

        return new Path(steps, (double) distance);
    }

//    public Path getShortestPath(String originNodeName, String destinationNodeName) {
//        Map<Node, Double> distances = new HashMap<>();
//        Map<Step, Node> previous = new HashMap<>();
//        Map<Node, Step> matchingSteps = new HashMap<>();
//        Step tmp;
//
//        nodeRepository.getAllNodes().forEach(node -> distances.put(node, Double.POSITIVE_INFINITY));
//        distances.put(nodeRepository.getNode(originNodeName), 0.0);
//        tmp = new Step(nodeRepository.getNode(originNodeName), null);
//        previous.put(tmp, null);
//        matchingSteps.put(nodeRepository.getNode(originNodeName), tmp);
//
//        List<Node> remaining = new ArrayList<>(nodeRepository.getAllNodes());
//        while (!remaining.isEmpty()) {
//            remaining.sort(Comparator.comparing(distances::get));
//            Node minimalNode = remaining.get(0);
//            remaining.remove(minimalNode);
//            executeDijkstra(distances, previous, minimalNode, matchingSteps);
//        }
//
//        return buildPath(distances, nodeRepository.getNode(destinationNodeName), previous, matchingSteps);
//    }
//
//    private void executeDijkstra(Map<Node, Double> distances, Map<Step, Node> previous, Node minimalNode,
//                                 Map<Node, Step> stepMap) {
//        Double currentDistance = distances.get(minimalNode);
//        final Step[] tmp = new Step[1];
//        minimalNode.getNeighborsLinks().stream()
//                .filter(link -> distances.get(link.getDestination()) > currentDistance + link.getDistance())
//                .forEach(link -> {
//                    distances.put(link.getDestination(), currentDistance + link.getDistance());
//                    tmp[0] = new Step(link.getDestination(), link);
//                    previous.put(tmp[0], minimalNode);
//                    stepMap.put(link.getDestination(), tmp[0]);
//                });
//    }
//
//    private Path buildPath(Map<Node, Double> distances, Node destinationNode, Map<Step, Node> previous,
//                           Map<Node, Step> stepMap) {
//
//        if (distances.get(destinationNode) == Double.POSITIVE_INFINITY) {
//            return new Path(new ArrayList<>(), Double.POSITIVE_INFINITY);
//        }
//
//        List<Step> path = new ArrayList<>();
//
//        path.add(stepMap.get(destinationNode));
//
//        Step currentStep = stepMap.get(destinationNode);
//
//        while ((currentStep = stepMap.get(previous.get(currentStep))) != null) {
//            path.add(0, currentStep);
//        }
//
//        return new Path(path, distances.get(destinationNode));
//    }

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

    public void computeFloydWarshall() {

        List<Node> nodes = nodeRepository.getAllNodes().stream().sorted().toList();

        FloydWarshallStep[][] steps = new FloydWarshallStep[nodes.size()][nodes.size()];

        for (Node node : nodes) {

            int index = nodes.indexOf(node);

            for (int i = 0; i < nodes.size(); i++) {
                steps[index][i] = new FloydWarshallStep(1_000_000, null, null);
            }

            steps[index][index] = new FloydWarshallStep(0, node, null);

            for (Link link : node.getNeighborsLinks()) {

                Node destination = link.getDestination();
                int distance = link.getDistance();

                steps[index][nodes.indexOf(destination)] = new FloydWarshallStep(distance, node, link);

            }

        }

        for (int k = 0; k < nodes.size(); k++) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    int newDistance = steps[i][k].distance + steps[k][j].distance;
                    if (newDistance < steps[i][j].distance) {
                        int finalJ = j;
                        Link link1 = steps[k][j].previous.getNeighborsLinks().stream()
                                .filter(link -> link.getDestination().equals(nodes.get(finalJ)))
                                .findFirst()
                                .get();
                        steps[i][j] = new FloydWarshallStep(newDistance, steps[k][j].previous, link1);
                    }
                }
            }
        }

        distanceRepository.storeDistances(steps);
    }

}
