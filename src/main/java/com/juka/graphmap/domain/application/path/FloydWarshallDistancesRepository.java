package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.model.path.FloydWarshallStep;

public interface FloydWarshallDistancesRepository {

    void storeDistances(FloydWarshallStep[][] distances);

    FloydWarshallStep[] getDistances(int index);

}
