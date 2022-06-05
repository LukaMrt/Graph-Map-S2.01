package com.juka.graphmap.view.swing.components;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Coordinate;
import com.juka.graphmap.domain.model.node.Flag;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.view.swing.SwingView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * Panel where the graph is drawn.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingGraphPanel extends JPanel {

    /**
     * The list of links.
     */
    private static final double NODE_SIZE = 20;

    /**
     * The list of nodes.
     */
    private final List<Node> nodes;

    /**
     * Constructor.
     *
     * @param nodes     the nodes to draw
     * @param swingView the screen
     */
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
                g2d.setStroke(new BasicStroke(1.5f));
                neighborCoordinate = neighborLink.getDestination().getCoordinate();

                switch (neighborLink.getType()) {
                    case NATIONAL -> g2d.setColor(new Color(255, 0, 0));
                    case HIGHWAY -> g2d.setColor(new Color(0, 255, 0));
                    case DEPARTMENTAL -> g2d.setColor(new Color(0, 0, 255));
                }

                if (neighborLink.isSelected()) {
                    g2d.setColor(new Color(0, 255, 255, 255));
                    g2d.setStroke(new BasicStroke(5.5f));
                }

                g2d.draw(new Line2D.Double(nodeCoordinate.x() + NODE_SIZE / 2, nodeCoordinate.y() + NODE_SIZE / 2,
                        neighborCoordinate.x() + NODE_SIZE / 2, neighborCoordinate.y() + NODE_SIZE / 2));
            }

        }
    }

    private void drawNodes(Graphics2D g2d, List<Node> nodes) {
        Coordinate nodeCoordinate;
        for (Node node : nodes) {
            double size = NODE_SIZE;
            nodeCoordinate = node.getCoordinate();

            if (node.getFlag() != Flag.NONE) {
                size = NODE_SIZE * 1.5;
            }

            g2d.setColor(new Color(0, 0, 0));
            g2d.drawString(node.getName(), (
                            nodeCoordinate.x() - node.getName().length() * 3.3f),
                    (float) (nodeCoordinate.y() - size / 3));

            switch (node.getType()) {
                case CITY -> g2d.setColor(new Color(0, 255, 0));
                case RECREATION_CENTER -> g2d.setColor(new Color(0, 0, 255));
                case RESTAURANT -> g2d.setColor(new Color(255, 0, 0));
            }

            if (node.getFlag() == Flag.MAIN) {
                g2d.setColor(new Color(0, 255, 255, 255));
            }

            if (node.getFlag() == Flag.SECONDARY) {
                g2d.setColor(new Color(255, 140, 60, 255));
            }

            g2d.fill(new Ellipse2D.Double(node.getCoordinate().x() - (size - NODE_SIZE) * 0.5, node.getCoordinate().y() - (size - NODE_SIZE) * 0.5, size, size));
        }
    }

}
