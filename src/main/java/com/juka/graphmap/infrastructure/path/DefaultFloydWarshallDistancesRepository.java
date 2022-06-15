package com.juka.graphmap.infrastructure.path;

import com.juka.graphmap.domain.application.path.FloydWarshallDistancesRepository;
import com.juka.graphmap.domain.model.path.FloydWarshallStep;

import javax.inject.Singleton;

/**
 * Default implementation of FloydWarshallDistancesRepository.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
@Singleton
public class DefaultFloydWarshallDistancesRepository implements FloydWarshallDistancesRepository {

    private FloydWarshallStep[][] distances;

    @Override
    public void storeDistances(FloydWarshallStep[][] distances) {
        this.distances = distances;
    }

    @Override
    public FloydWarshallStep[] getDistances(int index) {
        return distances[index];
    }

}
