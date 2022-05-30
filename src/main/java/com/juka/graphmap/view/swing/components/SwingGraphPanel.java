package com.juka.graphmap.view.swing.components;

import com.juka.graphmap.domain.model.node.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class SwingGraphPanel extends JPanel {

    private final List<Node> nodes;

    public SwingGraphPanel(List<Node> nodes) {
        super();
        this.nodes = nodes;
        setSize(700, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(10,10,50,50);
        g2d.draw(ellipse);
    }
}
