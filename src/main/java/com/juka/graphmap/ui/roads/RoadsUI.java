package com.juka.graphmap.ui.roads;

import java.util.List;

/**
 * Interface for the roads UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface RoadsUI {

    /**
     * Called when the users interact with the roads view.
     * Parameters are null if it's the first call of the method.
     *
     * @param start Name of the start node.
     * @param steps List of steps.
     * @param end   Name of the end node.
     */
    void interact(String start, List<String> steps, String end);

}
