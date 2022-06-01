package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;

import java.util.List;

/**
 * Interface for the direct neighbors view
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface DirectNeighborsView {

    /**
     * Called when the user interacts with the DirectNeighborsView.
     *
     * @param nodes                 The list of nodes to display
     * @param links                 The list of links to display
     * @param nodeCharacteristics   The node characteristics to display
     * @param linkCharacteristics   The link characteristics to display
     */
    void display(List<Node> nodes, List<String> links, NodeCharacteristics nodeCharacteristics, LinkCharacteristics linkCharacteristics);

}
