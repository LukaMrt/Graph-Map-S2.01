package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.List;

/**
 * Service to check if two nodes are at 2 distance.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeDistanceService {

    private final NodeRepository nodeRepository;
    private final PathService pathService;

    /**
     * Constructor of the NodeDistanceService.
     *
     * @param nodeRepository NodeRepository of the graph.
     * @param pathService    PathService of the graph.
     */
    @Inject
    public NodeDistanceService(NodeRepository nodeRepository, PathService pathService) {
        this.nodeRepository = nodeRepository;
        this.pathService = pathService;
    }

    /**
     * Get all the neighbors of the node at given distance.
     *
     * @param node     name of the node.
     * @param distance distance of the neighbors.
     * @return all the neighbors of the node at given distance.
     */
    public List<Node> getNDistanceNeighbors(String node, int distance) {

//        Stream<Node> stream = nodeRepository.getNode(node).getNeighbors().stream();
//
//        for (int i = 0; i < distance - 1; i++) {
//            stream = stream.map(Node::getNeighbors).flatMap(Collection::stream).distinct();
//        }
//
//        return stream.toList();

        return nodeRepository.getAllNodes().stream()
                .filter(n -> pathService.getShortestPath(node, n.getName()).getPath().size() - 1 == distance)
                .toList();
    }

}
