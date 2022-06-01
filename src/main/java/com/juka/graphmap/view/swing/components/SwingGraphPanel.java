package com.juka.graphmap.view.swing.components;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Coordinate;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.view.swing.SwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class SwingGraphPanel extends JPanel {

    private final List<Node> nodes;
    private final double nodeSize = 20;

    public SwingGraphPanel(List<Node> nodes, SwingView swingView) {
        super();
        this.nodes = nodes;
        addMouseListener(new MouseListener(nodes, swingView));
        setSize(700, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawLinks(g2d, nodes);
        drawNodes(g2d, nodes);
    }

    private void drawLinks(Graphics2D g2d, List<Node> nodes) {
        Coordinate nodeCoordinate;
        Coordinate neighborCoordinate;
        for (Node node : nodes) {
            nodeCoordinate = node.getCoordinate();

            for (Link neighborLink : node.getNeighborsLinks()) {
                neighborCoordinate = neighborLink.getDestination().getCoordinate();

                switch (neighborLink.getType()) {
                    case NATIONAL -> g2d.setColor(new Color(255, 0, 0));
                    case HIGHWAY -> g2d.setColor(new Color(0, 255, 0));
                    case DEPARTMENTAL -> g2d.setColor(new Color(0, 0, 255));
                }

                if (neighborLink.isSelected()) {
                    g2d.setColor(new Color(0, 0, 0, 255));
                }

                g2d.draw(new Line2D.Double(nodeCoordinate.x() + nodeSize / 2, nodeCoordinate.y() + nodeSize / 2,
                        neighborCoordinate.x() + nodeSize / 2, neighborCoordinate.y() + nodeSize / 2));
            }

        }
    }

    private void drawNodes(Graphics2D g2d, List<Node> nodes) {
        Coordinate nodeCoordinate;
        for (Node node : nodes) {
            nodeCoordinate = node.getCoordinate();

            g2d.setColor(new Color(0, 0, 0));
            g2d.drawString(node.getName(), (
                            nodeCoordinate.x() - node.getName().length() * 3.3f),
                    (float) (nodeCoordinate.y() - nodeSize / 3));

            switch (node.getType()) {
                case CITY -> g2d.setColor(new Color(0, 255, 0));
                case RECREATION_CENTER -> g2d.setColor(new Color(0, 0, 255));
                case RESTAURANT -> g2d.setColor(new Color(255, 0, 0));
            }

            if (node.isSelected()) {
                g2d.setColor(new Color(0, 0, 0, 255));
            }

            g2d.fill(new Ellipse2D.Double(node.getCoordinate().x(), node.getCoordinate().y(), nodeSize, nodeSize));
        }
    }

}
