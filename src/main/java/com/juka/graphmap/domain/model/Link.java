package com.juka.graphmap.domain.model;

/**
 * @author Luka Maret
 * @since 0.1.0
 *
 * Link class represents a link between two nodes.
 */
public class Link {

    private final String name;
    private final Node destination;
    private final LinkType type;
    private final int distance;

    public Link(String name, Node destination, LinkType type, int distance) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.distance = distance;
    }

    /**
     * Returns the destination node of this link.
     *
     * @return the destination node of this link
     */
    public Node getDestination() {
        return this.destination;
    }

    /**
     * Returns the distance of this link.
     *
     * @return the distance of this link
     */
    public LinkType getType() {
        return this.type;
    }

    /**
     * Returns the distance of this link.
     *
     * @return the distance of this link
     */
    public int getDistance() {
        return this.distance;
    }

    @Override
    public String toString() {
        return "Link{" +
                "destination=" + destination +
                ", type=" + type +
                ", distance=" + distance +
                '}';
    }

}
