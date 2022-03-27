package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface NodeRepository {

    void addNode(Node node);

    void encounterError();

    List<Node> getAllNodes();

    Node getNode(String name);

    boolean hasEncounteredError();

}
