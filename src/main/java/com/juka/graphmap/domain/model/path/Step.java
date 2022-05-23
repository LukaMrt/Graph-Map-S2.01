package com.juka.graphmap.domain.model.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.Objects;

public class Step {
    private final Node destination;
    private final Link originLink;

    public Step(Node destination, Link originLink) {
        this.destination = destination;
        this.originLink = originLink;
    }

    public Node getDestination() {
        return destination;
    }

    public Link getOriginLink() {
        return originLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (!Objects.equals(destination, step.destination)) return false;
        return Objects.equals(originLink, step.originLink);
    }

}
