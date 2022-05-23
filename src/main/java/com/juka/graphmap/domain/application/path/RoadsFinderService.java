package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

public interface RoadsFinderService {

    Path getPathsWithSpecificLocations(String start, List<String> steps, String end);

}
