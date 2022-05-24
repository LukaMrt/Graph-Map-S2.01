package com.juka.graphmap.domain.model.node;

import com.juka.graphmap.domain.model.link.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Node class represents a node in a graph.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
public class Node implements Comparable<Node> {

    private final List<Link> neighbors = new ArrayList<>();
    private final String name;
    private final NodeType type;
    private final int x;
    private final int y;

    /**
     * Constructor of the node.
     *
     * @param name name of the node
     * @param type type of the node
     * @param x x coordinate of the node
     * @param y y coordinate of the node
     */
    public Node(String name, NodeType type, int x, int y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
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

    /**
     * Returns the x coordinate of the node.
     *
     * @return the x coordinate of the node
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the node.
     *
     * @return the y coordinate of the node
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
