package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

/**
 * Interface for loading graph from file.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface GraphLoader {

    /**
     * Loads all the nodes from the file.
     *
     * @return The list of nodes.
     */
    List<Node> loadNodes();

    /**
     * Loads all the links from the file.
     *
     * @param nodeRepository the repository of nodes.
     * @return The list of links.
     */
    List<Link> loadLinks(NodeRepository nodeRepository);

}