package com.juka.graphmap.domain.model.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

public class FloydWarshallStep {

    public final int distance;
    public final Node previous;
    public final Link previousLink;

    public FloydWarshallStep(int distance, Node previous, Link previousLink) {
        this.distance = distance;
        this.previous = previous;
        this.previousLink = previousLink;
    }

    @Override
    public String toString() {
        if (distance == 1_000_000) {
            return "[INF]";
        }
        return "[" + distance + "/" + previous.getName() + "," + (previousLink == null ? "N" : "Y") + "]";
    }
}
