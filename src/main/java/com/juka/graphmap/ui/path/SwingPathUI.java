package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.link.LinkService;
import com.juka.graphmap.domain.application.node.NodeService;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Swing implementation of the path UI.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingPathUI implements PathUI {

    private final GraphService graphService;
    private final PathService pathService;
    private final NodeService nodeService;
    private final LinkService linkService;
    private final PathView view;

    /**
     * Constructor of the SwingPathUI.
     *
     * @param graphService the graph service
     * @param pathService  the path service
     * @param nodeService  the node service
     * @param linkService  the link service
     * @param view         the view
     */
    @Inject
    public SwingPathUI(GraphService graphService, PathService pathService, NodeService nodeService, LinkService linkService, PathView view) {
        this.graphService = graphService;
        this.pathService = pathService;
        this.nodeService = nodeService;
        this.linkService = linkService;
        this.view = view;
    }

    @Override
    public void interact(String start, String end) {

        Path path = new Path(new ArrayList<>(), 0.0);

        nodeService.unselectAll();

        if (start != null && end != null) {
            path = pathService.getShortestPath(start, end);
        }

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

        view.display(graphService.getAllNodes(), start, end, path);
    }

}
