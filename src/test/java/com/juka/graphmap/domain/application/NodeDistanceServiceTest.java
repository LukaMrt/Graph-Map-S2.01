package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.node.NodeDistanceService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NodeDistanceServiceTest {

    @Mock
    private NodeRepository nodeRepository;

    private NodeDistanceService nodeDistanceService;

    @BeforeEach
    void setUp() {
        nodeDistanceService = new NodeDistanceService(nodeRepository);
    }

    @Test
    void are2distance_shouldReturnFalse_when2NodesAreNotAt2Distance() {

        Node node1 = new Node("Node1", NodeType.CITY);
        when(nodeRepository.getNode("Node1")).thenReturn(node1);

        boolean result = nodeDistanceService.are2distance("Node1", "Node2");

        assertThat(result).isFalse();
    }

    @Test
    void are2distance_shouldReturnTrue_when2NodesAreAt2Distance() {

        Node node1 = new Node("Node1", NodeType.CITY);
        Node node2 = new Node("Node2", NodeType.CITY);
        Node node3 = new Node("Node3", NodeType.CITY);
        Link link1 = new Link("Link1", node3, LinkType.HIGHWAY, 10);
        Link link2 = new Link("Link2", node2, LinkType.HIGHWAY, 10);
        node1.addLink(link1);
        node3.addLink(link2);
        when(nodeRepository.getNode("Node1")).thenReturn(node1);

        boolean result = nodeDistanceService.are2distance("Node1", "Node2");

        assertThat(result).isTrue();
    }

}
