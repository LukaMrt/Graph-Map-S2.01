package com.juka.graphmap.domain.model.node;

import java.util.List;

public class NodeCharacteristics {

    public final String name;
    public final NodeType type;
    public final List<String> neighbors;

    public NodeCharacteristics(String name, NodeType type, List<String> neighbors) {
        this.name = name;
        this.type = type;
        this.neighbors = neighbors;
    }

}
