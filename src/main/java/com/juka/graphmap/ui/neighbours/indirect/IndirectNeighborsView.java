package com.juka.graphmap.ui.neighbours.indirect;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

/**
 * Interface for the view of the indirect neighbours.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface IndirectNeighborsView {

    /**
     * Displays the indirect neighbours view.
     *
     * @param nodes      the nodes to display
     * @param node       the name of the first node
     * @param neighbours the result of the indirect neighbours search
     */
    void display(List<Node> nodes, String node, int distance, List<Node> neighbours);

}
