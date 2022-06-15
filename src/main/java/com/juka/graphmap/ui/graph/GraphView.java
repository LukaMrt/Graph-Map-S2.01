package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

/**
 * Interface for graph view.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface GraphView {

    /**
     * Displays the view
     *
     * @param nodes the nodes to display
     * @param links the links to display
     */
    void display(List<Node> nodes, List<Link> links);

}
