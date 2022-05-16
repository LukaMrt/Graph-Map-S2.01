package com.juka.graphmap.ui.neighbours.indirect;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface IndirectNeighboursView {

    void displayNodes(List<Node> nodes, String location1, String location2, boolean result);

}
