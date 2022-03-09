package com.juka.graphmap.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    private final List<Link> neighbors = new ArrayList<>();
    private final NodeType type;
    private final String name;

    public Node(NodeType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void addLink(Link link) {
        this.neighbors.add(link);
    }

    public List<Node> getNeighbors() {
        return this.neighbors.stream()
                .map(Link::getDestination)
                .collect(Collectors.toList());
    }

    public NodeType getType() {
        return this.type;
    }

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
