package com.juka.graphmap.ui.roads;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.application.path.RoadsFinderService;
import com.juka.graphmap.domain.model.node.Flag;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing implementation of the roads UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingRoadsUI implements RoadsUI {

    private final GraphService graphService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final RoadsView roadsView;
    private final RoadsFinderService roadsFinderService;

    /**
     * Constructor the the swing roads UI.
     *
     * @param roadsView          The roads view.
     * @param graphService       The graph service.
     * @param nodeService        The node service.
     * @param linkService        The link service.
     * @param roadsFinderService The roads finder service.
     */
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

        linkService.unselectAll();
        for (Step step : path.getPath()) {
            nodeService.select(step.getDestination().getName(), Flag.SECONDARY);
            if (step.getOriginLink() != null) {
                linkService.select(step.getOriginLink().getRoadNameWithIndex());
            }
        }

        if (start != null && !start.isEmpty()) {
            nodeService.select(start, Flag.MAIN);
        }

        if (end != null && !end.isEmpty()) {
            nodeService.select(end, Flag.MAIN);
        }

        roadsView.display(graphService.getAllNodes(), steps, path, start, end);
    }

}
