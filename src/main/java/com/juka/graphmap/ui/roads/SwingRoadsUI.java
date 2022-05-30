package com.juka.graphmap.ui.roads;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.path.RoadsFinderService;
import com.juka.graphmap.domain.model.path.Path;

import javax.inject.Inject;
import java.util.List;

public class SwingRoadsUI implements RoadsUI {

    private final RoadsView roadsView;
    private final GraphService graphService;
    private final RoadsFinderService roadsFinderService;

    @Inject
    public SwingRoadsUI(RoadsView roadsView, GraphService graphService, RoadsFinderService roadsFinderService) {
        this.roadsView = roadsView;
        this.graphService = graphService;
        this.roadsFinderService = roadsFinderService;
    }

    @Override
    public void interact(String start, List<String> steps, String end) {

        Path path = start != null ? roadsFinderService.getPathsWithSpecificLocations(start, steps, end) : null;

        roadsView.display(graphService.getAllNodes(), steps, path);
    }

}
