package com.juka.graphmap.domain.application.path;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.domain.model.path.FloydWarshallStep;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

// TODO : rework it
@ExtendWith(MockitoExtension.class)
public class PathServiceTest {

    @Mock
    private NodeRepository nodeRepository;

    @Mock
    private FloydWarshallDistancesRepository distanceRepository;

    private PathService pathService;

    @BeforeEach
    void setUp() {
        pathService = new PathService(nodeRepository, distanceRepository);
    }

    @Test
    void getShortestPath_shouldReturnEmptyShortestPath_whenThereIsNoPathFromOriginNodeToDestinationNode() {
        Node origin = new Node("Lyon", NodeType.CITY, 0, 0);
        Node destination = new Node("New York", NodeType.CITY, 0, 0);
        FloydWarshallStep[] distances = {new FloydWarshallStep(0, origin, null), new FloydWarshallStep(1_000_000, null, null)};

        when(nodeRepository.getNode("Lyon")).thenReturn(origin);
        when(nodeRepository.getNode("New York")).thenReturn(destination);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.asList(origin, destination));
        when(distanceRepository.getDistances(0)).thenReturn(distances);

        Path path = pathService.getShortestPath("Lyon", "New York");
        Path expected = new Path(new ArrayList<>(), Double.POSITIVE_INFINITY);

        assertThat(path).isEqualTo(expected);
    }

    @Test
    void getShortestPath_shouldReturnShortestPathWithFourNodes_whenTheLengthOfThePathIsFour() {
        Node node1 = new Node("A", NodeType.RECREATION_CENTER, 0, 0);
        Node node2 = new Node("B", NodeType.CITY, 0, 0);
        Node node3 = new Node("C", NodeType.CITY, 0, 0);
        Node node4 = new Node("D", NodeType.RESTAURANT, 0, 0);

        Link linkLoop1 = new Link("A-B", node2, LinkType.DEPARTMENTAL, 6);
        Link linkLoop2 = new Link("B-A", node1, LinkType.HIGHWAY, 3);
        Link link1 = new Link("B-C", node3, LinkType.NATIONAL, 3);
        Link link2 = new Link("C-D", node4, LinkType.HIGHWAY, 2);
        Link link3 = new Link("D-B", node2, LinkType.NATIONAL, 4);

        node1.addLink(linkLoop1);
        node2.addLink(linkLoop2);
        node2.addLink(link1);
        node3.addLink(link2);
        node4.addLink(link3);

        FloydWarshallStep[] distances = {new FloydWarshallStep(0, node1, null), new FloydWarshallStep(6, node1, linkLoop1),
                new FloydWarshallStep(3, node2, link1), new FloydWarshallStep(11, node3, link2)};

        when(nodeRepository.getNode("A")).thenReturn(node1);
        when(nodeRepository.getNode("D")).thenReturn(node4);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.asList(node1, node2, node3, node4));
        when(distanceRepository.getDistances(0)).thenReturn(distances);

        Path path = pathService.getShortestPath("A", "D");

        Path expected = new Path(Arrays.asList(new Step(node1, null),
                new Step(node2, linkLoop1),
                new Step(node3, link1),
                new Step(node4, link2)), 11.0);
        assertThat(path).isEqualTo(expected);
    }

    @Test
    void computeFloydWarshall_shouldStoreNothing_whenThereIsNoNode() {
        when(nodeRepository.getAllNodes()).thenReturn(new ArrayList<>());

        pathService.computeFloydWarshall();

        verify(distanceRepository, only()).storeDistances(new FloydWarshallStep[0][0]);
    }

    @Test
    void computeFloydWarshall_shouldStoreInfinity_whenNodesAreNotLinked() {
        Node node1 = new Node("A", NodeType.RECREATION_CENTER, 0, 0);
        Node node2 = new Node("B", NodeType.CITY, 0, 0);
        Node node3 = new Node("C", NodeType.CITY, 0, 0);
        Node node4 = new Node("D", NodeType.RESTAURANT, 0, 0);

        when(nodeRepository.getAllNodes()).thenReturn(Arrays.asList(node1, node2, node3, node4));

        pathService.computeFloydWarshall();

        FloydWarshallStep[][] expected = {
                {new FloydWarshallStep(0, node1, null), new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(1_000_000, null, null)},
                {new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(0, node2, null), new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(1_000_000, null, null)},
                {new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(0, node3, null), new FloydWarshallStep(1_000_000, null, null)},
                {new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(0, node4, null)}
        };

        verify(distanceRepository, only()).storeDistances(argThat(argument -> Arrays.deepEquals(argument, expected)));
    }

    @Test
    void computeFloydWarshall_shouldStoreDistances_whenNodesAreLinked() {
        Node node1 = new Node("A", NodeType.RECREATION_CENTER, 0, 0);
        Node node2 = new Node("B", NodeType.CITY, 0, 0);
        Node node3 = new Node("C", NodeType.CITY, 0, 0);
        Node node4 = new Node("D", NodeType.RESTAURANT, 0, 0);

        Link linkLoop1 = new Link("A-B", node2, LinkType.NATIONAL, 1);
        Link linkLoop2 = new Link("B-A", node1, LinkType.NATIONAL, 1);
        Link link1 = new Link("B-C", node3, LinkType.NATIONAL, 3);
        Link link2 = new Link("C-D", node4, LinkType.HIGHWAY, 2);
        Link link3 = new Link("D-B", node2, LinkType.NATIONAL, 4);

        node1.addLink(linkLoop1);
        node2.addLink(linkLoop2);
        node2.addLink(link1);
        node3.addLink(link2);
        node4.addLink(link3);

        FloydWarshallStep[][] expected = {
                {new FloydWarshallStep(0, node1, null), new FloydWarshallStep(1, node1, linkLoop1), new FloydWarshallStep(4, node2, link1), new FloydWarshallStep(6, node3, link2)},
                {new FloydWarshallStep(1, node2, linkLoop2), new FloydWarshallStep(0, node2, null), new FloydWarshallStep(3, node2, link1), new FloydWarshallStep(5, node3, link2)},
                {new FloydWarshallStep(7, node2, linkLoop2), new FloydWarshallStep(6, node4, link3), new FloydWarshallStep(0, node3, null), new FloydWarshallStep(2, node3, link2)},
                {new FloydWarshallStep(5, node2, linkLoop2), new FloydWarshallStep(4, node4, link3), new FloydWarshallStep(7, node2, link1), new FloydWarshallStep(0, node4, null)}
        };

        when(nodeRepository.getAllNodes()).thenReturn(Arrays.asList(node1, node2, node3, node4));

        pathService.computeFloydWarshall();

        verify(distanceRepository, only()).storeDistances(argThat(argument -> Arrays.deepEquals(argument, expected)));
    }

    @Test
    void getPathsWithSpecificLocations_shouldReturnDirectPathFromStartToEnd_whenThereIsNoStep() {
        Node node1 = new Node("A", NodeType.RECREATION_CENTER, 0, 0);
        Node node2 = new Node("B", NodeType.CITY, 0, 0);

        Link link1 = new Link("A-B", node2, LinkType.NATIONAL, 1);

        node1.addLink(link1);

        when(nodeRepository.getNode("A")).thenReturn(node1);
        when(nodeRepository.getNode("B")).thenReturn(node2);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.asList(node1, node2));
        when(distanceRepository.getDistances(0)).thenReturn(new FloydWarshallStep[]{new FloydWarshallStep(0, node1, null), new FloydWarshallStep(1, node1, link1)});

        Path path = pathService.getPathsWithSpecificLocations("A", new ArrayList<>(), "B");

        assertThat(path).isEqualTo(new Path(Arrays.asList(new Step(node1, null), new Step(node2, link1)), 1.0));
    }

    @Test
    void getPathsWithSpecificLocations_shouldReturnPathFromStartToEndWithSteps_whenThereAreSteps() {
        Node node1 = new Node("A", NodeType.RECREATION_CENTER, 0, 0);
        Node node2 = new Node("B", NodeType.CITY, 0, 0);
        Node node3 = new Node("C", NodeType.CITY, 0, 0);

        Link link1 = new Link("A-B", node2, LinkType.NATIONAL, 1);
        Link link2 = new Link("B-C", node2, LinkType.NATIONAL, 1);
        Link link3 = new Link("C-B", node2, LinkType.NATIONAL, 1);

        node1.addLink(link1);
        node2.addLink(link2);
        node3.addLink(link3);

        when(nodeRepository.getNode("A")).thenReturn(node1);
        when(nodeRepository.getNode("B")).thenReturn(node2);
        when(nodeRepository.getNode("C")).thenReturn(node3);
        when(nodeRepository.getAllNodes()).thenReturn(Arrays.asList(node1, node2, node3));
        when(distanceRepository.getDistances(0)).thenReturn(new FloydWarshallStep[]{new FloydWarshallStep(0, node1, null), new FloydWarshallStep(1, node1, link1), new FloydWarshallStep(2, node2, link2)});
        when(distanceRepository.getDistances(1)).thenReturn(new FloydWarshallStep[]{new FloydWarshallStep(1_000_000, null, null), new FloydWarshallStep(0, node2, null), new FloydWarshallStep(1, node2, link2)});
        when(distanceRepository.getDistances(2)).thenReturn(new FloydWarshallStep[]{new FloydWarshallStep(0, node1, null), new FloydWarshallStep(1, node3, link3), new FloydWarshallStep(0, node3, null)});

        Path path = pathService.getPathsWithSpecificLocations("A", Arrays.asList("C", "B"), "C");

        assertThat(path).isEqualTo(new Path(Arrays.asList(new Step(node1, null), new Step(node2, link1), new Step(node3, link2), new Step(node2, link3), new Step(node3, link2)), 4.0));
    }

}
