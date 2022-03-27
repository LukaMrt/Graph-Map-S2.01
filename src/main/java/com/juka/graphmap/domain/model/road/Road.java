package com.juka.graphmap.domain.model.road;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import java.util.List;
import java.util.Objects;

public class Road {

    private final String name;
    private final List<Node> nodes;

    public Road(String name, List<Node> nodes) {
        this.name = name;
        this.nodes = nodes;
    }

    public String getName() {
        return name;
    }

    public long countCities() {
        return nodes.stream()
                .map(Node::getType)
                .filter(NodeType.CITY::equals)
                .count();
    }

    public long countRestaurants() {
        return nodes.stream()
                .map(Node::getType)
                .filter(NodeType.RESTAURANT::equals)
                .count();
    }

    public long countRecreationCenters() {
        return nodes.stream()
                .map(Node::getType)
                .filter(NodeType.RECREATION_CENTER::equals)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Objects.equals(name, road.name) && Objects.equals(nodes, road.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nodes);
    }

    @Override
    public String toString() {
        return "Road{" +
                "name='" + name + '\'' +
                ", nodes=" + nodes +
                '}';
    }

}
