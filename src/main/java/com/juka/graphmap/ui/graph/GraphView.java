package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface GraphView {
    void display();
    void displayNodes(NodeRepository nodeRepository);
    void displayLinks(NodeRepository nodeRepository);
}
