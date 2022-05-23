package com.juka.graphmap.domain.application.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.FloydWarshallStep;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;

import java.util.*;

public class PathService implements RoadsFinderService {

    private final NodeRepository nodeRepository;
    private final FloydWarshallDistancesRepository distanceRepository;

    @Inject
    public PathService(NodeRepository nodeRepository, FloydWarshallDistancesRepository distanceRepository) {
        this.nodeRepository = nodeRepository;
        this.distanceRepository = distanceRepository;
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
                                .orElse(new Link("", nodes.get(finalJ), LinkType.HIGHWAY, 0));
                        steps[i][j] = new FloydWarshallStep(newDistance, steps[k][j].previous, link1);
                    }
                }
            }
        }

        distanceRepository.storeDistances(steps);
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

    @Override
    public Path getPathsWithSpecificLocations(String start, List<String> steps, String end) {

        if (steps.size() == 0) {
            return getShortestPath(start, end);
        }

        Path path = getShortestPath(start, steps.get(0));

        for (int i = 1; i < steps.size(); i++) {
            Path path2 = getShortestPath(steps.get(i - 1), steps.get(i));
            path = path.merge(path2);
        }

        Path path3 = getShortestPath(steps.get(steps.size() - 1), end);
        path = path.merge(path3);

        return path;
    }

}
