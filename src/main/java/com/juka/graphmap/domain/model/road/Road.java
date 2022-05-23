package com.juka.graphmap.domain.model.road;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import java.util.List;
import java.util.Objects;

/**
 * Represents a road.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class Road {

    private final String name;
    private final List<Node> nodes;

    /**
     * Constructor of a road.
     *
     * @param name  name of the road
     * @param nodes list of nodes that compose the road
     */
    public Road(String name, List<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
    }

    /**
     * Returns the name of the road.
     *
     * @return name of the road
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of cities on the road.
     *
     * @return number of cities on the road
     */
    public long countCities() {
        return nodes.stream()
                .map(Node::getType)
                .filter(NodeType.CITY::equals)
                .count();
    }

    /**
     * Returns the number of restaurants on the road.
     *
     * @return number of restaurants on the road
     */
    public long countRestaurants() {
        return nodes.stream()
                .map(Node::getType)
                .filter(NodeType.RESTAURANT::equals)
                .count();
    }

    /**
     * Returns the number of recreation centers on the road.
     *
     * @return number of recreation centers on the road
     */
    public long countRecreationCenters() {
        return nodes.stream()
                .map(Node::getType)
                .filter(NodeType.RECREATION_CENTER::equals)
                .count();
    }

    /**
     * Check if the object is equal to this road.
     *
     * @param o object to compare
     * @return true if the object is equal to this road, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Objects.equals(name, road.name) && Objects.equals(nodes, road.nodes);
    }

    /**
     * Returns the hash code of this road.
     *
     * @return hash code of this road
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, nodes);
    }

    /**
     * Returns a string representation of this road.
     *
     * @return string representation of this road
     */
    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", nodes=" + nodes +
                '}';
    }

}
