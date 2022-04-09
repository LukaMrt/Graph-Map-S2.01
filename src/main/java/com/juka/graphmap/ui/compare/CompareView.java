package com.juka.graphmap.ui.compare;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;
import java.util.Map;

public interface CompareView {

    void displayEndOptions();

    void displayOptions();

    void displayNodes(List<Node> nodes);

    void displayComparaison(Map<String, Boolean> comparaison, Node node1, Node node2);

}
