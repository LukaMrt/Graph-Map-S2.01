package com.juka.graphmap.ui.neighbours.direct;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;

import java.util.List;

public interface DirectNeighborsView {

    void display(List<String> nodes, List<String> links, NodeCharacteristics nodeCharacteristics, LinkCharacteristics linkCharacteristics);

}
