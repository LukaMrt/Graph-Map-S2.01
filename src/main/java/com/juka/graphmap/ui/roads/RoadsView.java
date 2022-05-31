package com.juka.graphmap.ui.roads;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

public interface RoadsView {

    void display(List<Node> nodes, List<String> steps, Path path, String start, String end);

}
