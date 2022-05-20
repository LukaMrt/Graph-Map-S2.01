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
public class NodeServiceTest {

    @Mock
    private NodeRepository nodeRepository;

    private NodeService nodeService;

    @BeforeEach
    public void setUp() {
        nodeService = new NodeService(nodeRepository);
    }

    @Test
    void getDirectNeighbours_shouldReturnEmptyList_whenNodeHasNoNeighbor() {

        Node node = new Node("Node", NodeType.CITY);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Node> neighbours = nodeService.getDirectNeighbours("Node");

        assertThat(neighbours).isEmpty();
    }

    @Test
    void getDirectNeighbours_shouldReturnListWithOneElement_whenNodeHasOneNeighbor() {

        Node node = new Node("Node", NodeType.CITY);
        Node neighbour = new Node("Neighbour", NodeType.CITY);
        Link link = new Link("Link", neighbour, LinkType.HIGHWAY, 10);
        node.addLink(link);
        when(nodeRepository.getNode("Node")).thenReturn(node);
        
        List<Node> neighbours = nodeService.getDirectNeighbours("Node");

        assertThat(neighbours).hasSize(1);
    }

    @Test
    void getType_shouldReturnMatchingNodeType() {
        Node node = new Node("Node", NodeType.RECREATION_CENTER);
        when(nodeRepository.getNode("Node")).thenReturn(node);
        assertThat(nodeService.getType("Node")).isEqualTo(NodeType.RECREATION_CENTER);
    }

    @Test
    void getDirectNeighborsLinks_shouldReturnEmptyList_whenNodeHasNoNeighbor() {

        Node node = new Node("Node", NodeType.CITY);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Link> neighboursLinks = nodeService.getDirectNeighborsLinks("Node");

        assertThat(neighboursLinks).isEmpty();
    }


    @Test
    void getDirectNeighborsLinks_shouldReturnListWithOneElement_whenNodeHasOneNeighbor() {
        Node node = new Node("Node", NodeType.CITY);
        Node neighbour = new Node("Neighbour", NodeType.CITY);
        Link link = new Link("Link", neighbour, LinkType.HIGHWAY, 10);
        node.addLink(link);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Link> neighboursLinks = nodeService.getDirectNeighborsLinks("Node");

        assertThat(neighboursLinks).hasSize(1);
    }

}
