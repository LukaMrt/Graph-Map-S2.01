package com.juka.graphmap.ui.path;

import com.juka.graphmap.domain.model.path.Path;

import java.util.List;

public interface PathView {

    void display(List<String> nodes, String node1, String node2, Path path);

}
