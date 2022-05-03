package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.node.Node;

import java.util.Map;

public interface CompareView {

    void display();

    void displayNodes(NodeRepository nodeRepository);

    void displayComparaison(Map<String, Boolean> comparaison, Node node1, Node node2);

}
