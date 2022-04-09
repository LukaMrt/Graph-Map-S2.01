package com.juka.graphmap.domain.model.exception;

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
