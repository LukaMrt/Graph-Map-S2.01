package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface DirectNeighboursView {

    void display();

    void displayNodes(List<Node> nodes);

    void displayNeighbours(List<Node> neighbours, String node);

    void displayLinks(List<String> links);

    void displayLinkCharacteristics(LinkCharacteristics characteristics);

}
