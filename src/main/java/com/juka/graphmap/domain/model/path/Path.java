package com.juka.graphmap.domain.model.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a path between two nodes.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class Path {

    private final List<Step> path;
    private final Double distance;

    /**
     * Constructor of the path.
     *
     * @param path The path.
     * @param distance The distance between the two nodes.
     */
    public Path(List<Step> path, Double distance) {
        this.path = path;
        this.distance = distance;
    }

    /**
     * Returns the path.
     *
     * @return The path.
     */
    public List<Step> getPath() {
        return path;
    }

    /**
     * Returns the distance between the two nodes.
     *
     * @return The distance between the two nodes.
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Returns the string representation of this path.
     *
     * @return The string representation of this path.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path that = (Path) o;
        return path.equals(that.path) && Objects.equals(distance, that.distance);
    }

    public Path merge(Path path2) {

        path2.getPath().remove(0);

        List<Step> mergedPath = new ArrayList<>(this.getPath());
        mergedPath.addAll(path2.getPath());
        return new Path(mergedPath, this.getDistance() + path2.getDistance());
    }

}
