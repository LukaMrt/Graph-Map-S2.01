package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Node;

import java.util.List;

public interface NodeRepository {

    Node getNode(String name);

    List<Node> getAllNodes();

    boolean hasEncounteredError();

}
