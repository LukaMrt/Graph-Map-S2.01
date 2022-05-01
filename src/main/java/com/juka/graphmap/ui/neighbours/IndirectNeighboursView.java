package com.juka.graphmap.ui.neighbours;

import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface IndirectNeighboursView {

    void display();

    void displayNodes(List<Node> nodes);

    void displayResult(String location1, String location2, boolean result);

}
