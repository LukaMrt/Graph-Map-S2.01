package com.juka.graphmap.domain.model.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node characteristics.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeCharacteristics {

    /**
     * The name of the node.
     */
    public final String name;
    /**
     * The type of the node.
     */
    public final String type;
    /**
     * The list of the node's neighbors.
     */
    public final List<String> neighbors;

    /**
     * Constructor of the NodeCharacteristics.
     *
     * @param name      name of the node
     * @param type      type of the node
     * @param neighbors list of neighbors
     */
    public NodeCharacteristics(String name, String type, List<String> neighbors) {
        this.name = name;
        this.type = type;
        this.neighbors = neighbors;
    }

    /**
     * Builds an empty node characteristics.
     *
     * @return the empty node characteristics
     */
    public static NodeCharacteristics empty() {
        return new NodeCharacteristics("", "", new ArrayList<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeCharacteristics that = (NodeCharacteristics) o;

        if (!name.equals(that.name)) return false;
        if (!type.equals(that.type)) return false;
        return neighbors.equals(that.neighbors);
    }

}
