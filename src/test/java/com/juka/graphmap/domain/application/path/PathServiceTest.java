package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.road.Road;
import com.juka.graphmap.domain.model.step.Step;
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
public class PathServiceTest {

    @Mock
    private NodeRepository nodeRepository;

    @Mock
    private LinkRepository linkRepository;

    private PathService pathService;

    @BeforeEach
    void setUp() {
        pathService = new PathService(nodeRepository, linkRepository);
    }

    @Test
    void getShortestPath_shouldReturnEmptyShortestPath_whenThereIsNoPathFromOriginNodeToDestinationNode() {
        Node origin = new Node("Lyon", NodeType.CITY);
        Node destination = new Node("New York", NodeType.CITY);

        when(nodeRepository.getNode("Lyon")).thenReturn(origin);
        when(nodeRepository.getNode("New York")).thenReturn(destination);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.stream(new Node[]{origin, destination}).toList());

        Path path = pathService.getShortestPath("Lyon", "New York");
        Path expected = new Path(new ArrayList<>(), Double.POSITIVE_INFINITY);

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
        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2, node3, node4));

        Path path = pathService.getShortestPath("A", "D");

        Path expected = new Path(List.of(new Step(node1, null),
                new Step(node2, linkLoop1),
                new Step(node3, link1),
                new Step(node4, link2)), 11.0);
        assertThat(path).isEqualTo(expected);
    }

    @Test
    void getPathsWithSpecificLocations_shouldReturnEmptyList_whenThereIsNotPathWithSpecificLocations() {

        when(linkRepository.getAllLinks()).thenReturn(new ArrayList<>());

        List<Road> paths = pathService.getPathsWithSpecificLocations(1, 1, 0);

        assertThat(paths).isEqualTo(new ArrayList<>());
    }

    @Test
    void getPathsWithSpecificLocations_should1Road_whenThereIs1PathWithSpecificLocations() {

        Node node1 = new Node("A", NodeType.RESTAURANT);
        Node node2 = new Node("B", NodeType.CITY);
        Link link1 = new Link("Road1.1A", node2, LinkType.HIGHWAY, 2);
        Link link2 = new Link("Road1.1B", node1, LinkType.HIGHWAY, 2);
        node1.addLink(link1);
        node2.addLink(link2);

        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2));
        when(linkRepository.getAllLinks()).thenReturn(List.of(link1, link2));

        List<Road> paths = pathService.getPathsWithSpecificLocations(1, 1, 0);
        List<Road> expected = List.of(new Road("Road1", List.of(node1, node2)));

        assertThat(paths).isEqualTo(expected);
    }

    @Test
    void getPathsWithSpecificLocations_should2Road_whenThereAre2PathsWithSpecificLocations() {

        Node node1 = new Node("A", NodeType.RESTAURANT);
        Node node2 = new Node("B", NodeType.RESTAURANT);
        Node node3 = new Node("C", NodeType.RESTAURANT);
        Link link1 = new Link("Road1.1A", node2, LinkType.HIGHWAY, 2);
        Link link2 = new Link("Road1.1B", node1, LinkType.HIGHWAY, 2);
        Link link3 = new Link("Road2.1A", node3, LinkType.HIGHWAY, 2);
        Link link4 = new Link("Road2.1B", node2, LinkType.HIGHWAY, 2);
        node1.addLink(link1);
        node2.addLink(link2);
        node2.addLink(link3);
        node3.addLink(link4);

        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2, node3));
        when(linkRepository.getAllLinks()).thenReturn(List.of(link1, link2, link3, link4));

        List<Road> paths = pathService.getPathsWithSpecificLocations(0, 1, 0);
        List<Road> expected = List.of(new Road("Road1", List.of(node1, node2)), new Road("Road2", List.of(node2, node3)));

        assertThat(paths).isEqualTo(expected);
    }

}
