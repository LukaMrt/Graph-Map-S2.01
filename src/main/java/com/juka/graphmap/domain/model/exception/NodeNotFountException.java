package com.juka.graphmap.domain.model.exception;

/**
 * Raised when the node doesn't exist.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class NodeNotFountException extends RuntimeException {

    private final String node;

    public NodeNotFountException(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Node " + node + " not found";
    }

}
