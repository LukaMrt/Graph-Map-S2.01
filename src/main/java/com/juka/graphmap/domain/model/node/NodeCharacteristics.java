package com.juka.graphmap.domain.model.node;

import java.util.ArrayList;
import java.util.List;

public class NodeCharacteristics {

    public final String name;
    public final String type;
    public final List<String> neighbors;

    public NodeCharacteristics(String name, String type, List<String> neighbors) {
        this.name = name;
        this.type = type;
        this.neighbors = neighbors;
    }

    public static NodeCharacteristics empty() {
        return new NodeCharacteristics("", "", new ArrayList<>());
    }

}
