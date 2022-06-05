package com.juka.graphmap.ui.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

/**
 * Interface for the home view.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface HomeView {

    /**
     * Displays the home view.
     *
     * @param graphCharacteristics the graph characteristics
     * @param nodes                the nodes to display
     */
    void display(GraphCharacteristics graphCharacteristics, List<Node> nodes);

}
