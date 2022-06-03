package com.juka.graphmap.ui.neighbours.indirect;

/**
 * Interface for the IndirectNeighborsUI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface IndirectNeighborsUI {

    /**
     * Called when the user interacts with the indirect neighbors view.
     * Parameters are null if it's the first call of the method.
     *
     * @param node    the node to study
     * @param distance the distance between the then nodes and the neighbors
     */
    void interact(String node, int distance);

}
