package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.path.Path;

public interface PathView {

    void display();

    void displayNodes(NodeRepository nodeRepository);

    void displayPath(Path path);

}
