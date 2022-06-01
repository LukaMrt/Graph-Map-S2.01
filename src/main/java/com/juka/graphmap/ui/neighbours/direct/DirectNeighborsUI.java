package com.juka.graphmap.ui.neighbours.direct;

/**
 * Interface for the direct neighbors UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface DirectNeighborsUI {

    /**
     * Called when the user interacts with the DirectNeighborsUI.
     * Parameters are null if it's the first call of the method.
     *
     * @param node The name of the node the user choose
     * @param link The name of the link the user choose
     */
    void interact(String node, String link);

}
