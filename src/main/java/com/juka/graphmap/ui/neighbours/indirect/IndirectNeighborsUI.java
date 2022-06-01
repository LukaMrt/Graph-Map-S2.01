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
     * @param node1 the first node chosen by the user
     * @param node2 the second node chosen by the user
     */
    void interact(String node1, String node2);

}
