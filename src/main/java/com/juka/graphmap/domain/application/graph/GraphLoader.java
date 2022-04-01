package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;

import java.util.List;

public interface GraphLoader {

    List<Node> loadNodes();

    List<Link> loadLinks(NodeRepository nodeRepository);

}