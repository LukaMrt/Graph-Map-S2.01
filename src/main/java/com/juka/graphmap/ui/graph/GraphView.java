package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.application.graph.NodeRepository;

public interface GraphView {
    void display();
    void displayNodes(NodeRepository nodeRepository);
    void displayLinks(NodeRepository nodeRepository);
}
