package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

public interface PathView {

    void display(List<Node> nodes, Node node1, Node node2, Path path);

}
