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
    private final Coordinate coordinate;
    private Flag flag;

    /**
     * Constructor of the node with coordinate.
     *
     * @param name name of the node
     * @param type type of the node
     * @param abscissa x coordinate of the node
     * @param ordinate y coordinate of the node
     */
    public Node(String name, NodeType type, int abscissa, int ordinate) {
        this.name = name;
        this.type = type;
        this.coordinate = new Coordinate(abscissa, ordinate);
        this.flag = Flag.NONE;
    }

    /**
     * Constructor of the node without coordinate.
     *
     * @param name name of the node
     * @param type type of the node
     */
    public Node(String name, NodeType type) {
        this.name = name;
        this.type = type;
        this.coordinate = null;
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
     * Returns the coordinate of the node.
     *
     * @return the coordinate of the node
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Returns true if the node is selected.
     *
     * @return true if the node is selected
     */
    public Flag getFlag() {
        return this.flag;
    }

    /**
     * Sets the node to selected.
     *
     * @param flag flag to set
     */
    public void flag(Flag flag) {
        this.flag = flag;
    }

    /**
     * Sets the node to unselected.
     */
    public void unFlag() {
        this.flag = Flag.NONE;
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
