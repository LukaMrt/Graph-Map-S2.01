package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface GraphView {
    void displayMenu();
    void displayNodes(List<Node> nodes);
    void displayLinksHeader();
    void displayLink(Node origin, Link link);
}
