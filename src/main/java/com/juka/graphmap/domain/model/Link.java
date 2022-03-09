package com.juka.graphmap.domain.model;

public class Link {

    private final Node destination;
    private final LinkType type;
    private final int distance;

    public Link(Node destination, LinkType type, int distance) {
        this.destination = destination;
        this.type = type;
        this.distance = distance;
    }

    public Node getDestination() {
        return this.destination;
    }

    public LinkType getType() {
        return this.type;
    }

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
