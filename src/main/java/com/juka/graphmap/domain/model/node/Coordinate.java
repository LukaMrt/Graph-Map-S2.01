package com.juka.graphmap.domain.model.node;

/**
 * Represents a node's coordinate.
 *
 * @author Julien Linget
 */
public class Coordinate {

    private final int x;
    private final int y;

    /**
     * Constructor of the coordinate.
     *
     * @param x the abscissa
     * @param y the ordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the abscissa.
     *
     * @return the abscissa
     */
    public int x() {
        return x;
    }

    /**
     * Gets the ordinate.
     *
     * @return the ordinate
     */

    public int y() {
        return y;
    }

}
