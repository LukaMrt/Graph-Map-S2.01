package com.juka.graphmap.ui.roads;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.application.path.RoadsFinderService;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SwingRoadsUI implements RoadsUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final RoadsView roadsView;
    private final RoadsFinderService roadsFinderService;

    @Inject
    public SwingRoadsUI(RoadsView roadsView, GraphService graphService, NodeService nodeService, LinkService linkService, RoadsFinderService roadsFinderService) {
        this.roadsView = roadsView;
        this.graphService = graphService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.roadsFinderService = roadsFinderService;
    }

    @Override
    public void interact(String start, List<String> steps, String end) {

        Path path = new Path(new ArrayList<>(), 0d);

        if (start != null && !start.equals("") && end != null && !end.isEmpty()) {
            path = roadsFinderService.getPathsWithSpecificLocations(start, steps, end);
        }

        nodeService.unselectAll();

        if (start != null && !start.isEmpty()) {
            nodeService.select(start);
        }

        if (end != null && !end.isEmpty()) {
            nodeService.select(end);
        }

        linkService.unselectAll();
        for (Step step : path.getPath()) {
            nodeService.select(step.getDestination().getName());
            if (step.getOriginLink() != null) {
                linkService.select(step.getOriginLink().getRoadNameWithIndex());
            }
        }

        roadsView.display(graphService.getAllNodes(), steps, path, start, end);
    }

}
