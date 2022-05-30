package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;

import java.util.List;

public interface DirectNeighborsView {

    void display(List<Node> nodes, List<String> links, NodeCharacteristics nodeCharacteristics, LinkCharacteristics linkCharacteristics);

}
