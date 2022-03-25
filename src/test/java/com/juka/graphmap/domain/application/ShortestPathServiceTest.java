package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Link;
import com.juka.graphmap.domain.model.LinkType;
import com.juka.graphmap.domain.model.Node;
import com.juka.graphmap.domain.model.NodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShortestPathServiceTest {
    @Mock
    private NodeRepository nodeRepository;

    private ShortestPathService shortestPathService;

    @BeforeEach
    void setUp() {
        shortestPathService = new ShortestPathService(nodeRepository);
    }

    @Test
    void getShortestPath_shouldReturnEmptyShortestPath_whenThereIsNoPathFromOriginNodeToDestinationNode() {
        Node origin = new Node("Lyon", NodeType.CITY);
        Node destination = new Node("New York", NodeType.CITY);

        when(nodeRepository.getNode("Lyon")).thenReturn(origin);
        when(nodeRepository.getNode("New York")).thenReturn(destination);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.stream(new Node[]{origin, destination}).toList());

        ShortestPath path = shortestPathService.getShortestPath("Lyon", "New York");
        ShortestPath expected = new ShortestPath(new ArrayList<>(), Double.POSITIVE_INFINITY);

        assertThat(path).isEqualTo(expected);
    }

    @Test
    void getShortestPath_shouldReturnShortestPathWithFourNodes_whenTheLengthOfThePathIsFour() {
        Node node1 = new Node("A", NodeType.RECREATION_CENTER);
        Node node2 = new Node("B", NodeType.CITY);
        Node node3 = new Node("C", NodeType.CITY);
        Node node4 = new Node("D", NodeType.RESTAURANT);

        Link linkLoop1 = new Link("A-B", node2, LinkType.DEPARTMENTAL, 6);
        Link linkLoop2 = new Link("B-A", node1, LinkType.HIGHWAY, 3);
        Link link1 = new Link("B-C", node3, LinkType.NATIONAL, 3);
        Link link2 = new Link ("C-D", node4, LinkType.HIGHWAY, 2);
        Link link3 = new Link("D-B", node2, LinkType.NATIONAL, 4);

        node1.addLink(linkLoop1);
        node2.addLink(linkLoop2);
        node2.addLink(link1);
        node3.addLink(link2);
        node4.addLink(link3);

        when(nodeRepository.getNode("A")).thenReturn(node1);
        when(nodeRepository.getNode("D")).thenReturn(node4);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.stream(new Node[]{node1, node2, node3, node4}).toList());

        ShortestPath path = shortestPathService.getShortestPath("A", "D");
        Node[] expectedPath = {node1, node2, node3, node4};
        ShortestPath expected = new ShortestPath(Arrays.stream(expectedPath).toList(), 11.0);
        assertThat(path).isEqualTo(expected);
    }
}
