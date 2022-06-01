package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.List;

/**
 * Service to get Node neighbors and characteristics.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeService {

    private final NodeRepository nodeRepository;

    /**
     * Constructor of the NodeService.
     *
     * @param nodeRepository NodeRepository of the graph.
     */
    @Inject
    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    /**
     * Get the direct neighbors of a node.
     *
     * @param node Name of the node.
     * @return list of neighbors of the node.
     */
    public List<Node> getDirectNeighbours(String node) {
        return nodeRepository.getNode(node).getNeighbors();
    }

    /**
     * Get the direct neighbors links of a node.
     *
     * @param node Name of the node.
     * @return list of neighbors links of the node.
     */
    public List<Link> getDirectNeighborsLinks(String node) {
        return nodeRepository.getNode(node).getNeighborsLinks();
    }

    /**
     * Returns the type of the node with the given name.
     *
     * @param node Name of the node.
     * @return Type of the node.
     */
    public NodeType getType(String node) {
        return nodeRepository.getNode(node).getType();
    }

    /**
     * Returns the node with the given name.
     *
     * @param name Name of the node.
     * @return Node with the given name.
     */
    public Node getNode(String name) {
        return nodeRepository.getNode(name);
    }

    /**
     * Returns the node characteristics of a given node name.
     *
     * @param name Name of the node.
     * @return Node characteristics of the node.
     */
    public NodeCharacteristics getNodeCharacteristics(String name) {
        Node node = getNode(name);

        if (node == null) {
            return NodeCharacteristics.empty();
        }

        List<String> neighbors = node.getNeighbors().stream()
                .map(Node::getName)
                .distinct()
                .toList();

        return new NodeCharacteristics(node.getName(), node.getType().toString(), neighbors);
    }

    /**
     * Unselects all the nodes.
     */
    public void unselectAll() {
        nodeRepository.getAllNodes().forEach(Node::unselect);
    }

    /**
     * Select the specified node.
     *
     * @param node Name of the node.
     */
    public void select(String node) {
        nodeRepository.getNode(node).select();
    }

}
