package com.juka.graphmap.domain.model.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.Objects;

/**
 * Represents a step in the Floyd-Warshall algorithm.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class FloydWarshallStep {

    /**
     * The distance between the two nodes.
     */
    public final int distance;

    /**
     * The previous node.
     */
    public final Node previous;

    /**
     * The last link of the path
     */
    public final Link previousLink;

    /**
     * Constructor of the Floyd and Warshall step.
     *
     * @param distance     The distance between the two nodes.
     * @param previous     The previous node in the path.
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
        return "[" + distance + "/" + previous.getName() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FloydWarshallStep other = (FloydWarshallStep) obj;
        return this.distance == other.distance
                && Objects.equals(this.previous, other.previous)
                && Objects.equals(this.previousLink, other.previousLink);
    }

}
