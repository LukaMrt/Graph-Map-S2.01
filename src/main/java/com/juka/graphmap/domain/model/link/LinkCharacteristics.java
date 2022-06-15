package com.juka.graphmap.domain.model.link;

import java.util.Objects;

/**
 * Represents the characteristics of a link.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class LinkCharacteristics {

    /**
     * The name of the link.
     */
    public final String name;
    /**
     * The start node name of the link.
     */
    public final String start;
    /**
     * The end node name of the link.
     */
    public final String end;
    /**
     * The type of the link
     */
    public final String type;
    /**
     * The distance of the link.
     */
    public final int distance;

    /**
     * Constructor of a LinkCharacteristics object.
     *
     * @param name     name of the link
     * @param start    start node
     * @param end      end node
     * @param type     type of the link
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

    @Override
    public String toString() {
        return "LinkCharacteristics{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", type='" + type + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkCharacteristics that = (LinkCharacteristics) o;
        return distance == that.distance && Objects.equals(name, that.name) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end, type, distance);
    }

}
