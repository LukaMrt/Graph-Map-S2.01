package com.juka.graphmap.infrastructure;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;

import java.util.ArrayList;
import java.util.List;

public class DefaultNodeRepository implements NodeRepository {

    private final List<Node> nodes = new ArrayList<>();
    private boolean error = false;

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void encounterError() {
        error = true;
    }

    @Override
    public List<Node> getAllNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public Node getNode(String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean hasEncounteredError() {
        return error;
    }

}
