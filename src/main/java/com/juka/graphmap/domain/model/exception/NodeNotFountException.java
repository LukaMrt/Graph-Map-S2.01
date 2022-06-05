package com.juka.graphmap.domain.model.exception;

/**
 * Raised when the node doesn't exist.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeNotFountException extends RuntimeException {

    /**
     * Name of the unfound node.
     */
    private final String node;

    /**
     * Constructor of the NodeNotFountException.
     *
     * @param node name of the unfound node
     */
    public NodeNotFountException(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Node " + node + " not found";
    }

}
