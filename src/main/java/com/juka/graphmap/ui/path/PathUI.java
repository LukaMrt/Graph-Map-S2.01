package com.juka.graphmap.ui.path;

/**
 * Interface for path UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface PathUI {

    /**
     * Called when the user interacts with the path view.
     * Parameters are null it's the first call of the method.
     *
     * @param nodeName1 the name of the first node chosen by the user
     * @param nodeName2 the name of the second node chosen by the user
     */
    void interact(String nodeName1, String nodeName2);

}
