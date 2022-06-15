package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.model.path.FloydWarshallStep;

/**
 * Interface for Floyd-Warshall distance repository that will store all distances.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
public interface FloydWarshallDistancesRepository {

    /**
     * Stores the distance from a matrix.
     *
     * @param distances matrix of distances of the Floyd-Warshall algorithm
     */
    void storeDistances(FloydWarshallStep[][] distances);

    /**
     * Returns the distance from a matrix.
     *
     * @param index index of the node
     * @return Line of distances of the Floyd-Warshall algorithm
     */
    FloydWarshallStep[] getDistances(int index);

}
