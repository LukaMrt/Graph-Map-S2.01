package com.juka.graphmap.ui.compare;

/**
 * Interface to process user input from the compare view.
 *
 * @author Julien Linget
 * @since 0.1.0
 */
public interface CompareUI {

    /**
     * Called when the user interacts with the compare view.
     * Parameters are null if it's the first call of the method.
     *
     * @param city1 The first city to compare.
     * @param city2 The second city to compare.
     */
    void interact(String city1, String city2);

}
