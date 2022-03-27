package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;
import java.util.Objects;

public class Path {

    private final List<Node> path;
    private final Double distance;

    public Path(List<Node> path, Double distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<Node> getPath() {
        return path;
    }

    public Double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path that = (Path) o;
        return path.equals(that.path) && Objects.equals(distance, that.distance);
    }
}