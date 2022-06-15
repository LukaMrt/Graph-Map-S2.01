package com.juka.graphmap.domain.model.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.Objects;

/**
 * Represents a step in the Dijkstra algorithm.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class Step {
    private final Node destination;
    private final Link originLink;

    /**
     * Constructor of a step.
     *
     * @param destination The destination node.
     * @param originLink  The origin link.
     */
    public Step(Node destination, Link originLink) {
        this.destination = destination;
        this.originLink = originLink;
    }

    /**
     * Returns the destination node.
     *
     * @return The destination node.
     */
    public Node getDestination() {
        return destination;
    }

    /**
     * Returns the origin link.
     *
     * @return The origin link.
     */
    public Link getOriginLink() {
        return originLink;
    }

    /**
     * Returns the string representation of this step.
     *
     * @return The string representation of this step.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (!Objects.equals(destination, step.destination)) return false;
        return Objects.equals(originLink, step.originLink);
    }

}
