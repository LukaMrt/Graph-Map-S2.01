package com.juka.graphmap.domain.model.link;

/**
 * Represents the characteristics of a link.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class LinkCharacteristics {

    public final String name;
    public final String start;
    public final String end;
    public final String type;
    public final int distance;

    /**
     * Constructor of a LinkCharacteristics object.
     *
     * @param name name of the link
     * @param start start node
     * @param end end node
     * @param type type of the link
     * @param distance distance between the two nodes
     */
    public LinkCharacteristics(String name, String start, String end, String type, int distance) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.type = type;
        this.distance = distance;
    }

    /**
     * Builds an empty link characteristics object.
     *
     * @return an empty link characteristics object
     */
    public static LinkCharacteristics empty() {
        return new LinkCharacteristics("", "", "", "", 0);
    }

}
