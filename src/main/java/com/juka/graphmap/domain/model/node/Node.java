package com.juka.graphmap.domain.model.node;

import com.juka.graphmap.domain.model.link.Link;

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
    private final String name;
    private final NodeType type;

    public Node(String name, NodeType type) {
        this.name = name;
        this.type = type;
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
     * Returns all links linked to this node
     *
     * @return a list of links
     */
    public List<Link> getNeighborsLinks() {
        return this.neighbors;
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
