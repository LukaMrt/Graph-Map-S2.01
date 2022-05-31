package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

/**
 * Interface of the service that will find the shortest path between two nodes with checkpoints.
 */
public interface RoadsFinderService {

    /**
     * Finds the shortest path between two nodes with checkpoints.
     *
     * @param start name of the start node
     * @param steps list of checkpoints
     * @param end name of the end node
     * @return shortest path between two nodes with checkpoints
     */
    Path getPathsWithSpecificLocations(String start, List<String> steps, String end);

}
