package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Flag;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.domain.model.node.NodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

        Node node = new Node("Node", NodeType.CITY, 0, 0);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Node> neighbours = nodeService.getDirectNeighbours("Node");

        assertThat(neighbours).isEmpty();
    }

    @Test
    void getDirectNeighbours_shouldReturnListWithOneElement_whenNodeHasOneNeighbor() {

        Node node = new Node("Node", NodeType.CITY, 0, 0);
        Node neighbour = new Node("Neighbour", NodeType.CITY, 0, 0);
        Link link = new Link("Link", neighbour, LinkType.HIGHWAY, 10);
        node.addLink(link);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Node> neighbours = nodeService.getDirectNeighbours("Node");

        assertThat(neighbours).hasSize(1);
    }

    @Test
    void getType_shouldReturnMatchingNodeType() {
        Node node = new Node("Node", NodeType.RECREATION_CENTER, 0, 0);
        when(nodeRepository.getNode("Node")).thenReturn(node);
        assertThat(nodeService.getType("Node")).isEqualTo(NodeType.RECREATION_CENTER);
    }

    @Test
    void getDirectNeighborsLinks_shouldReturnEmptyList_whenNodeHasNoNeighbor() {

        Node node = new Node("Node", NodeType.CITY, 0, 0);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Link> neighboursLinks = nodeService.getDirectNeighborsLinks("Node");

        assertThat(neighboursLinks).isEmpty();
    }

    @Test
    void getDirectNeighborsLinks_shouldReturnListWithOneElement_whenNodeHasOneNeighbor() {
        Node node = new Node("Node", NodeType.CITY, 0, 0);
        Node neighbour = new Node("Neighbour", NodeType.CITY, 0, 0);
        Link link = new Link("Link", neighbour, LinkType.HIGHWAY, 10);
        node.addLink(link);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        List<Link> neighboursLinks = nodeService.getDirectNeighborsLinks("Node");

        assertThat(neighboursLinks).hasSize(1);
    }

    @Test
    void getNode_shouldReturnNode() {
        Node node = new Node("Node", NodeType.CITY, 0, 0);

        when(nodeRepository.getNode("Node")).thenReturn(node);

        assertThat(nodeService.getNode("Node")).isEqualTo(node);
    }

    @Test
    void getNode_shouldReturnNode2() {
        Node node = new Node("Node2", NodeType.CITY, 0, 0);

        when(nodeRepository.getNode("Node2")).thenReturn(node);

        assertThat(nodeService.getNode("Node2")).isEqualTo(node);
    }

    @Test
    void getNodeCharacteristics_shouldReturnNodeCharacteristics() {
        Node node = new Node("Node", NodeType.CITY, 0, 0);

        NodeCharacteristics expected = new NodeCharacteristics("Node", NodeType.CITY.toString(), Collections.emptyList());
        when(nodeRepository.getNode("Node")).thenReturn(node);

        assertThat(nodeService.getNodeCharacteristics("Node")).isEqualTo(expected);
    }

    @Test
    void getNodeCharacteristics_shouldReturnEmptyNodeCharacteristics_whenNodeIsNull() {
        assertThat(nodeService.getNodeCharacteristics(null)).isEqualTo(new NodeCharacteristics("", "", new ArrayList<>()));
    }

    @Test
    void getNodeCharacteristics_shouldReturnNodeCharacteristics2() {
        Node node = new Node("Node2", NodeType.RECREATION_CENTER, 0, 0);
        Node neighbour = new Node("Neighbour", NodeType.CITY, 0, 0);
        Link link = new Link("Link", neighbour, LinkType.HIGHWAY, 10);
        node.addLink(link);

        NodeCharacteristics expected = new NodeCharacteristics("Node2", NodeType.RECREATION_CENTER.toString(), Collections.singletonList("Neighbour"));
        when(nodeRepository.getNode("Node2")).thenReturn(node);

        assertThat(nodeService.getNodeCharacteristics("Node2")).isEqualTo(expected);
    }

    @Test
    void unselectAll_shouldUnselectAllNodes_whenNodesAreSelected() {
        Node node = new Node("Node", NodeType.CITY, 0, 0);
        node.flag(Flag.MAIN);
        when(nodeRepository.getAllNodes()).thenReturn(Collections.singletonList(node));

        nodeService.unselectAll();

        assertThat(node.getFlag()).isEqualTo(Flag.NONE);
    }

    @Test
    void select_shouldSelectNode_whenNodeExist() {
        Node node = new Node("Node", NodeType.CITY, 0, 0);
        when(nodeRepository.getNode("Node")).thenReturn(node);

        nodeService.select("Node", Flag.SECONDARY);

        assertThat(node.getFlag()).isEqualTo(Flag.SECONDARY);
    }

}
