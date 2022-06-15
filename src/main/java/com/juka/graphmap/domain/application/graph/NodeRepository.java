package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

/**
 * Interface of the node repository.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface NodeRepository {

    /**
     * Add a node to the repository.
     *
     * @param node the node to add
     */
    void addNode(Node node);

    /**
     * Sets the error flag to true.
     */
    void encounterError();

    /**
     * Returns all nodes in the repository.
     *
     * @return all nodes in the repository
     */
    List<Node> getAllNodes();

    /**
     * Returns the node with the given name
     *
     * @param name the name of the node
     * @return the node with the given name
     */
    Node getNode(String name);

    /**
     * Returns true if the repository encountered an error.
     *
     * @return true if the repository encountered an error
     */
    boolean hasEncounteredError();

}
