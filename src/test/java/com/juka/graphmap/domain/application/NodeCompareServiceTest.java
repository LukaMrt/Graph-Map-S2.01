package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.node.NodeCompareService;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NodeCompareServiceTest {
    @Mock
    private NodeRepository nodeRepository;

    private NodeCompareService nodeCompareService;

    @BeforeEach
    public void setUp() {
        nodeCompareService = new NodeCompareService(nodeRepository);
    }

    @Test
    void nodeCompare_shouldReturnMapFilledWithFalse_whenNode1HasNoNeighbor() {
        Node node1 = new Node("Node1", NodeType.CITY);
        Node node2 = new Node("Node2", NodeType.CITY);
        when(nodeRepository.getNode("Node1")).thenReturn(node1);
        when(nodeRepository.getNode("Node2")).thenReturn(node2);

        Map<String, Boolean> results = nodeCompareService.nodeCompareCity("Node1", "Node2");

        assertThat(results).doesNotContainValue(true);
    }

    @Test
    void nodeCompare_shouldReturnMapWithOpenTrue_whenNode1IsMoreOpen() {
        Node node1 = new Node("City1", NodeType.CITY);
        Node pass = new Node("Passage point", NodeType.RESTAURANT);
        Node city = new Node("CityAt2Distance", NodeType.CITY);
        Node node2 = new Node("City2", NodeType.CITY);

        Link link1 = new Link("Link1", pass, LinkType.HIGHWAY, 10);
        Link link2 = new Link("Link2", city, LinkType.DEPARTMENTAL, 20);

        node1.addLink(link1);
        pass.addLink(link2);

        when(nodeRepository.getNode("City1")).thenReturn(node1);
        when(nodeRepository.getNode("City2")).thenReturn(node2);

        Map<String, Boolean> results = nodeCompareService.nodeCompareCity("City1", "City2");

        assertThat(results).containsEntry("open", true)
                .containsEntry("cultural", false)
                .containsEntry("gastronomic", false);
    }

    @Test
    void nodeCompare_shouldReturnMapWithOpenTrueAndCulturalTrue_whenNode1IsMoreOpenAndMoreCultural() {
        Node node1 = new Node("City1", NodeType.CITY);
        Node pass = new Node("Passage point", NodeType.RESTAURANT);
        Node city = new Node("CityAt2Distance", NodeType.CITY);
        Node recreation = new Node("ParkAt2Distance", NodeType.RECREATION_CENTER);

        Node node2 = new Node("City2", NodeType.CITY);

        Link link1 = new Link("Link1", pass, LinkType.HIGHWAY, 10);
        Link link2 = new Link("Link2", city, LinkType.DEPARTMENTAL, 20);
        Link link3 = new Link("Link3", recreation, LinkType.DEPARTMENTAL, 10);

        node1.addLink(link1);
        pass.addLink(link2);
        pass.addLink(link3);

        when(nodeRepository.getNode("City1")).thenReturn(node1);
        when(nodeRepository.getNode("City2")).thenReturn(node2);

        Map<String, Boolean> results = nodeCompareService.nodeCompareCity("City1", "City2");

        assertThat(results).containsEntry("open", true)
                .containsEntry("cultural", true)
                .containsEntry("gastronomic", false);
    }

    @Test
    void nodeCompare_ShouldReturnMapFilledWithTrue_whenNode1IsMoreOpenAndMoreCulturalAndMoreGastronomic() {
        Node node1 = new Node("City1", NodeType.CITY);
        Node pass = new Node("Passage point", NodeType.RESTAURANT);
        Node city = new Node("CityAt2Distance", NodeType.CITY);
        Node recreation = new Node("ParkAt2Distance", NodeType.RECREATION_CENTER);
        Node restaurant = new Node("RestaurantAt2Distance", NodeType.RESTAURANT);

        Node node2 = new Node("City2", NodeType.CITY);

        Link link1 = new Link("Link1", pass, LinkType.HIGHWAY, 10);
        Link link2 = new Link("Link2", city, LinkType.DEPARTMENTAL, 20);
        Link link3 = new Link("Link3", recreation, LinkType.DEPARTMENTAL, 10);
        Link link4 = new Link("Link4", restaurant, LinkType.NATIONAL, 10);

        node1.addLink(link1);
        pass.addLink(link2);
        pass.addLink(link3);
        pass.addLink(link4);

        when(nodeRepository.getNode("City1")).thenReturn(node1);
        when(nodeRepository.getNode("City2")).thenReturn(node2);

        Map<String, Boolean> results = nodeCompareService.nodeCompareCity("City1", "City2");

        assertThat(results).doesNotContainValue(false);
    }
}
