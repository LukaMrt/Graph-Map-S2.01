package com.juka.graphmap.domain.model.exception;

/**
 * Raised when the node doesn't exist.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeNotFoundException extends RuntimeException {

    /**
     * Name of the not found node.
     */
    public final String node;

    /**
     * Constructor of the NodeNotFountException.
     *
     * @param node name of the unfound node
     */
    public NodeNotFoundException(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Node " + node + " not found";
    }

}
