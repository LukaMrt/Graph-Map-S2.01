package com.juka.graphmap.ui.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface HomeView {

    void display(GraphCharacteristics graphCharacteristics, List<Node> nodes);

}
