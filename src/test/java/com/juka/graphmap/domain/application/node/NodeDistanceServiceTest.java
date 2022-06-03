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

import java.util.List;

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
    void getNDistanceNeighbors_shouldReturn2DirectNeighbors_whenNodeHas2DirectNeighbors() {

        Node node1 = new Node("Node1", NodeType.CITY, 0, 0);
        Node node2 = new Node("Node2", NodeType.CITY, 0, 0);
        Node node3 = new Node("Node3", NodeType.CITY, 0, 0);

        Link link1 = new Link("Link1", node2, LinkType.HIGHWAY, 0);
        Link link2 = new Link("Link2", node3, LinkType.HIGHWAY, 0);

        node1.addLink(link1);
        node1.addLink(link2);

        when(nodeRepository.getNode("Node1")).thenReturn(node1);

        List<Node> result = nodeDistanceService.getNDistanceNeighbors("Node1", 1);

        assertThat(result).containsExactly(node2, node3);
    }

    @Test
    void getNDistanceNeighbors_shouldReturn1NeighbourIn2Distance_whenNodeHas1NeighbourAt2Distance() {

        Node node1 = new Node("Node1", NodeType.CITY, 0, 0);
        Node node2 = new Node("Node2", NodeType.CITY, 0, 0);
        Node node3 = new Node("Node3", NodeType.CITY, 0, 0);

        Link link1 = new Link("Link1", node2, LinkType.HIGHWAY, 0);
        Link link2 = new Link("Link2", node3, LinkType.HIGHWAY, 0);

        node1.addLink(link1);
        node2.addLink(link2);

        when(nodeRepository.getNode("Node1")).thenReturn(node1);

        List<Node> result = nodeDistanceService.getNDistanceNeighbors("Node1", 2);

        assertThat(result).containsExactly(node3);
    }

}
