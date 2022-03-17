package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Node;

public interface NodeRepository {

    Node getNode(String name);

}
