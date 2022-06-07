package com.juka.graphmap.domain.application.path;

import com.google.inject.Inject;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.FloydWarshallStep;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service to find the shortest path between two nodes with checkpoints.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
public class PathService implements RoadsFinderService {

    private final NodeRepository nodeRepository;
    private final FloydWarshallDistancesRepository distanceRepository;

    /**
     * Constructor of the path service.
     *
     * @param nodeRepository     repository of nodes
     * @param distanceRepository repository of distances
     */
    @Inject
    public PathService(NodeRepository nodeRepository, FloydWarshallDistancesRepository distanceRepository) {
        this.nodeRepository = nodeRepository;
        this.distanceRepository = distanceRepository;
    }

    /**
     * Executes the Floyd-Warshall algorithm to fill the distances repository.
     */
    public void computeFloydWarshall() {

        List<Node> nodes = nodeRepository.getAllNodes().stream().sorted().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        FloydWarshallStep[][] steps = new FloydWarshallStep[nodes.size()][nodes.size()];

        for (Node node : nodes) {
            initializeSteps(steps, node, nodes);
        }

        for (int k = 0; k < nodes.size(); k++) {
            for (int i = 0; i < nodes.size(); i++) {
                iteration(nodes, steps, k, i);
            }
        }

        distanceRepository.storeDistances(steps);
    }

    /**
     * Initializes the steps of the Floyd-Warshall algorithm.
     *
     * @param steps matrix of steps
     * @param node  node to initialize
     * @param nodes list of nodes
     */
    private void initializeSteps(FloydWarshallStep[][] steps, Node node, List<Node> nodes) {
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

    /**
     * Executes one iteration of the Floyd-Warshall algorithm.
     *
     * @param nodes list of nodes
     * @param steps matrix of steps
     * @param k     index of the node
     * @param i     index of the node
     */
    private void iteration(List<Node> nodes, FloydWarshallStep[][] steps, int k, int i) {
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

    /**
     * Returns the shortest path between two nodes.
     *
     * @param originNodeName      name of the origin node
     * @param destinationNodeName name of the destination node
     * @return shortest path between two nodes
     */
    public Path getShortestPath(String originNodeName, String destinationNodeName) {

        Node node = nodeRepository.getNode(originNodeName);
        Node destination = nodeRepository.getNode(destinationNodeName);

        List<Node> nodes = nodeRepository.getAllNodes().stream().sorted().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        FloydWarshallStep[] distances = distanceRepository.getDistances(nodes.indexOf(node));

        int distance = distances[nodes.indexOf(node)].distance;
        List<Step> steps = new ArrayList<>();

        Node currentNode = destination;

        while (currentNode != node) {
            int currentNodeIndex = nodes.indexOf(currentNode);
            int previousNodeIndex = nodes.indexOf(distances[currentNodeIndex].previous);
            Link previousLink = distances[currentNodeIndex].previousLink;

            if (distances[currentNodeIndex].distance >= 1_000_000) {
                return new Path(new ArrayList<>(), Double.POSITIVE_INFINITY);
            }

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
