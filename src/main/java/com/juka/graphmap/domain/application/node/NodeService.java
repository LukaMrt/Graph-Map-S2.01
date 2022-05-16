package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.List;

public class NodeService {

    private final NodeRepository nodeRepository;

    @Inject
    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getDirectNeighbours(String node) {
        return nodeRepository.getNode(node).getNeighbors();
    }

    public List<Link> getDirectNeighborsLinks(String node) {
        return nodeRepository.getNode(node).getNeighborsLinks();
    }

    public NodeType getType(String node) {
        return nodeRepository.getNode(node).getType();
    }

    public Node getNode(String name) {
        return nodeRepository.getNode(name);
    }

    public NodeCharacteristics getNodeCharacteristics(String name) {
        Node node = getNode(name);
        List<String> neighbors = node.getNeighbors().stream()
                .map(Node::getName)
                .distinct()
                .toList();

        return new NodeCharacteristics(node.getName(), node.getType(), neighbors);
    }

}
