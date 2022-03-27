package com.juka.graphmap.domain.model.link;

import com.juka.graphmap.domain.model.node.Node;

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

    /**
     * Returns the name of this link with the index.
     *
     * @return the name of this link with the index
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of this link without the index.
     *
     * @return the name of this link without the index
     */
    public String getRoadName() {
        return name.substring(0, name.indexOf("."));
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", destination=" + destination +
                ", type=" + type +
                ", distance=" + distance +
                '}';
    }

}
