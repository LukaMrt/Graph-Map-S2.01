package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
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
    void areNDistance_shouldReturnFalse_whenNodesAreNotAt2Distance() {

        Node node1 = new Node("Node1", NodeType.CITY, 0, 0);
        when(nodeRepository.getNode("Node1")).thenReturn(node1);

        boolean result = nodeDistanceService.areNDistance("Node1", "Node2", 2);

        assertThat(result).isFalse();
    }

    @Test
    void areNDistance_shouldReturnTrue_whenNodesAreAt3Distance() {

        Node node1 = new Node("Node1", NodeType.CITY, 0, 0);
        Node node2 = new Node("Node2", NodeType.CITY, 0, 0);
        Node node3 = new Node("Node3", NodeType.CITY, 0, 0);
        Node node4 = new Node("Node4", NodeType.CITY, 0, 0);
        Link link1 = new Link("Link1", node3, LinkType.HIGHWAY, 10);
        Link link2 = new Link("Link2", node2, LinkType.HIGHWAY, 10);
        Link link3 = new Link("Link3", node4, LinkType.HIGHWAY, 10);
        node1.addLink(link1);
        node3.addLink(link2);
        node2.addLink(link3);
        when(nodeRepository.getNode("Node1")).thenReturn(node1);

        boolean result = nodeDistanceService.areNDistance("Node1", "Node4", 3);

        assertThat(result).isTrue();
    }

}
