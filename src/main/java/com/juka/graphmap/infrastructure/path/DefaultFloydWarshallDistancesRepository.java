package com.juka.graphmap.infrastructure.path;

import com.juka.graphmap.domain.application.path.FloydWarshallDistancesRepository;
import com.juka.graphmap.domain.model.path.FloydWarshallStep;

import javax.inject.Singleton;

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
