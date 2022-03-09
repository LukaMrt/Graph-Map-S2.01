package com.juka.graphmap.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Luka Maret
 * @since 0.1.0
 *
 * Node class represents a node in a graph.
 */
public class Node {

    private final List<Link> neighbors = new ArrayList<>();
    private final NodeType type;
    private final String name;

    public Node(NodeType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Adds a link to the node.
     *
     * @param link the link to add
     */
    public void addLink(Link link) {
        this.neighbors.add(link);
    }

    /**
     * Returns all nodes linked to this node.
     *
     * @return a list of nodes
     */
    public List<Node> getNeighbors() {
        return this.neighbors.stream()
                .map(Link::getDestination)
                .collect(Collectors.toList());
    }

    /**
     * Returns the type of the node.
     *
     * @return the type of the node
     */
    public NodeType getType() {
        return this.type;
    }

    /**
     * Returns the name of the node.
     *
     * @return the name of the node
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }

}
