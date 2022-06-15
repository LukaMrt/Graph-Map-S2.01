package com.juka.graphmap.domain.model.link;

import com.juka.graphmap.domain.model.node.Node;

/**
 * Link class represents a link between two nodes.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class Link {

    private final String name;
    private final Node destination;
    private final LinkType type;
    private final int distance;
    private boolean selected;

    /**
     * Constructor of a link.
     *
     * @param name        name of the link
     * @param destination destination node
     * @param type        type of the link
     * @param distance    distance between the two nodes
     */
    public Link(String name, Node destination, LinkType type, int distance) {
        this.name = name;
        this.destination = destination;
        this.type = type;
        this.distance = distance;
        this.selected = false;
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

    /**
     * Returns the name of this link without the last .1 or .2.
     *
     * @return the name of this link without the index
     */
    public String getRoadNameWithIndex() {
        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * Returns true if the link is selected, false otherwise.
     *
     * @return true if the link is selected, false otherwise
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Sets the state of the link to selected.
     */
    public void select() {
        this.selected = true;
    }

    /**
     * Sets the state of the link to unselected.
     */
    public void unselect() {
        this.selected = false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Link && ((Link) obj).getName().equals(this.getName());
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
