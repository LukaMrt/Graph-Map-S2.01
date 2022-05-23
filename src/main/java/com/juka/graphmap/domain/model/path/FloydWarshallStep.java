package com.juka.graphmap.domain.model.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

/**
 * Represents a step in the Floyd-Warshall algorithm.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class FloydWarshallStep {

    public final int distance;
    public final Node previous;
    public final Link previousLink;

    /**
     * Constructor of the Floyd and Warshall step.
     *
     * @param distance The distance between the two nodes.
     * @param previous The previous node in the path.
     * @param previousLink The previous link in the path.
     */
    public FloydWarshallStep(int distance, Node previous, Link previousLink) {
        this.distance = distance;
        this.previous = previous;
        this.previousLink = previousLink;
    }

    /**
     * Returns the string representation of this step.
     *
     * @return The string representation of this step.
     */
    @Override
    public String toString() {
        if (distance == 1_000_000) {
            return "[INF]";
        }
        return "[" + distance + "/" + previous.getName() + "," + (previousLink == null ? "N" : "Y") + "]";
    }
}
