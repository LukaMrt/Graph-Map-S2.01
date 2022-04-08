package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

public interface PathView {

    void displayChoice();
    void displayNodes(List<Node> nodes);
    void displayPath(Path path);
    void displayEndMenu();
}
