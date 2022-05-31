package com.juka.graphmap.view.swing.components;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Coordinate;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.view.swing.GlobalSwingView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MouseListener implements java.awt.event.MouseListener {

    private final List<Node> nodes;
    private final GlobalSwingView globalSwingView;

    public MouseListener(List<Node> nodes, GlobalSwingView globalSwingView) {
        this.nodes = nodes;
        this.globalSwingView = globalSwingView;
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        Node clickedNode = nodes.stream()
                .filter(node -> isClicked(event, node))
                .findFirst()
                .orElse(null);

        Link2 clickedLink = retrieveLinks(nodes)
                .stream().filter(link -> isClicked(event, link))
                .findFirst()
                .orElse(new Link2(null, null));

        BiConsumer<Node, Link> action = (node, link) -> globalSwingView.leftClick(clickedNode, clickedLink.link);

        if (SwingUtilities.isRightMouseButton(event)) {
            action = (node, link) -> globalSwingView.rightClick(clickedNode, clickedLink.link);
        }

        if (event.isShiftDown()) {
            action = (node, link) -> globalSwingView.shiftClick(clickedNode, clickedLink.link);
        }

        action.accept(clickedNode, clickedLink.link);
    }

    private boolean isClicked(MouseEvent event, Link2 link) {

        Coordinate source = link.from.getCoordinate();
        Coordinate destination = link.link.getDestination().getCoordinate();

        double x1 = source.x() + 10;
        double y1 = source.y() + 10;
        double x2 = destination.x() + 10;
        double y2 = destination.y() + 10;

        double x3 = event.getX();
        double y3 = event.getY();

        return Line2D.ptSegDistSq(x1, y1, x2, y2, x3, y3) < 100
                && min(x1, x2) <= x3 && x3 <= max(x1, x2)
                && min(y1, y2) <= y3 && y3 <= max(y1, y2)
                && !isClicked(event, link.from)
                && !isClicked(event, link.link.getDestination());
    }

    private boolean isClicked(MouseEvent event, Node node) {
        Coordinate coordinate = node.getCoordinate();
        return event.getPoint().distanceSq(coordinate.x() + 10, coordinate.y() + 10) < 100;
    }

    private List<Link2> retrieveLinks(List<Node> nodes) {

        List<Link2> links = new ArrayList<>();

        for (Node node : nodes) {
            for (Link neighborLink : node.getNeighborsLinks()) {
                if (links.stream().noneMatch(link -> link.link().getRoadNameWithIndex().equals(neighborLink.getRoadNameWithIndex()))) {
                    links.add(new Link2(node, neighborLink));
                }
            }
        }

        return links;
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    private record Link2(Node from, Link link) {
    }

}