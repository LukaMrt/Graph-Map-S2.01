package com.juka.graphmap.ui.graph;

import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface GraphView {

    void display(List<Node> nodes, List<LinkCharacteristics> links);

}
