package com.juka.graphmap.ui.roads;

import com.juka.graphmap.domain.model.road.Road;

import java.util.List;

public interface RoadsView {

    void display(List<Road> roads, int cities, int restaurants, int recreationCenters);

}
