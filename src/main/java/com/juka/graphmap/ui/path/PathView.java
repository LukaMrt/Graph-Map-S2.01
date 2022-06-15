package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

/**
 * Interface for the path view.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface PathView {

    /**
     * Display the path view.
     *
     * @param nodes the nodes to display
     * @param node1 the first node, null if it's the first call of the method
     * @param node2 the second node, null if it's the first call of the method
     * @param path  the path to display, null if it's the first call of the method
     */
    void display(List<Node> nodes, String node1, String node2, Path path);

}
