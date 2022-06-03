package com.juka.graphmap.domain.application.node;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.path.Step;
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

    @Mock
    private PathService pathService;

    private NodeDistanceService nodeDistanceService;

    @BeforeEach
    void setUp() {
        nodeDistanceService = new NodeDistanceService(nodeRepository, pathService);
    }

    @Test
    void getNDistanceNeighbors_shouldReturn2DirectNeighbors_whenNodeHas2DirectNeighbors() {

        Node node1 = new Node("Node1", NodeType.CITY, 0, 0);
        Node node2 = new Node("Node2", NodeType.CITY, 0, 0);
        Node node3 = new Node("Node3", NodeType.CITY, 0, 0);

        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2, node3));
        when(pathService.getShortestPath("Node1", "Node1")).thenReturn(new Path(List.of(new Step(null, null)), 0d));
        when(pathService.getShortestPath("Node1", "Node2")).thenReturn(new Path(List.of(new Step(null, null), new Step(null, null)), 0d));
        when(pathService.getShortestPath("Node1", "Node3")).thenReturn(new Path(List.of(new Step(null, null), new Step(null, null)), 0d));

        List<Node> result = nodeDistanceService.getNDistanceNeighbors("Node1", 1);

        assertThat(result).containsExactly(node2, node3);
    }

    @Test
    void getNDistanceNeighbors_shouldReturn1NeighbourIn2Distance_whenNodeHas1NeighbourAt2Distance() {

        Node node1 = new Node("Node1", NodeType.CITY, 0, 0);
        Node node2 = new Node("Node2", NodeType.CITY, 0, 0);
        Node node3 = new Node("Node3", NodeType.CITY, 0, 0);

        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2, node3));
        when(pathService.getShortestPath("Node1", "Node1")).thenReturn(new Path(List.of(new Step(null, null)), 0d));
        when(pathService.getShortestPath("Node1", "Node2")).thenReturn(new Path(List.of(new Step(null, null), new Step(null, null)), 0d));
        when(pathService.getShortestPath("Node1", "Node3")).thenReturn(new Path(List.of(new Step(null, null), new Step(null, null), new Step(null, null)), 0d));

        List<Node> result = nodeDistanceService.getNDistanceNeighbors("Node1", 2);

        assertThat(result).containsExactly(node3);
    }

}
