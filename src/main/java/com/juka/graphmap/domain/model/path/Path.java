package com.juka.graphmap.domain.model.path;

import java.util.List;
import java.util.Objects;

public class Path {

    private final List<Step> path;
    private final Double distance;

    public Path(List<Step> path, Double distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<Step> getPath() {
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
