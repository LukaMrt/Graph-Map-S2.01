package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.model.road.Road;

import java.util.List;

public interface RoadsFinderService {

    List<Road> getPathsWithSpecificLocations(int cityCount, int restaurantCount, int recreationCenterCount);

}
