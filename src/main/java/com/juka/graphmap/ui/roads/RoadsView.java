package com.juka.graphmap.ui.roads;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

/**
 * Interface for the roads view.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface RoadsView {

    /**
     * Displays the roads view
     *
     * @param nodes List of nodes to display
     * @param steps List of steps to display, null if it's the first call of the method
     * @param path  Path to display, null if it's the first call of the method
     * @param start Start node name, null if it's the first call of the method
     * @param end   End node name, null if it's the first call of the method
     */
    void display(List<Node> nodes, List<String> steps, Path path, String start, String end);

}
