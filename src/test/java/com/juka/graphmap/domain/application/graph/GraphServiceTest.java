package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
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

import static com.juka.graphmap.domain.model.graph.GraphCharacteristicsBuilder.aGraphCharacteristics;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GraphServiceTest {

    private GraphService graphService;

    @Mock
    private NodeRepository nodeRepository;

    @Mock
    private LinkRepository linkRepository;

    @Mock
    private GraphLoader loader;
    @BeforeEach
    void setUp() {
        graphService = new GraphService(nodeRepository, linkRepository, loader);
    }

    @Test
    void countCities_shouldReturn1_whenGraphHas1City() {

        Node node1 = new Node("node1", NodeType.CITY);
        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1));

        int result = graphService.countCities();

        assertThat(result).isEqualTo(1);
    }

    @Test
    void countCities_shouldReturn2_whenGraphHas2Cities() {

        Node node1 = new Node("node1", NodeType.CITY);
        Node node2 = new Node("node2", NodeType.CITY);
        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2));

        int result = graphService.countCities();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void countRoads_shouldReturn1_whenGraphHas1Road() {

        Node node1 = new Node("node1", NodeType.CITY);
        Link link1 = new Link("link1", node1, LinkType.HIGHWAY, 1);
        Link link2 = new Link("link2", node1, LinkType.HIGHWAY, 1);
        when(linkRepository.getAllLinks()).thenReturn(List.of(link1, link2));

        int result = graphService.countRoads();

        assertThat(result).isEqualTo(1);
    }

    @Test
    void countRoads_shouldReturn2_whenGraphHas2Roads() {

        Node node1 = new Node("node1", NodeType.CITY);
        Link link1 = new Link("link1", node1, LinkType.HIGHWAY, 1);
        Link link2 = new Link("link2", node1, LinkType.HIGHWAY, 1);
        Link link3 = new Link("link3", node1, LinkType.HIGHWAY, 1);
        Link link4 = new Link("link4", node1, LinkType.HIGHWAY, 1);
        when(linkRepository.getAllLinks()).thenReturn(List.of(link1, link2, link3, link4));

        int result = graphService.countRoads();

        assertThat(result).isEqualTo(2);
    }

    @Test
    void getPercentageOfLocationType_shouldReturn10Percent_whenGraphHas10PercentOfCities() {

        Node node1 = new Node("node1", NodeType.CITY);
        Node node2 = new Node("node2", NodeType.RECREATION_CENTER);
        Node node3 = new Node("node3", NodeType.RECREATION_CENTER);
        Node node4 = new Node("node4", NodeType.RECREATION_CENTER);
        Node node5 = new Node("node5", NodeType.RECREATION_CENTER);
        Node node6 = new Node("node6", NodeType.RECREATION_CENTER);
        Node node7 = new Node("node7", NodeType.RECREATION_CENTER);
        Node node8 = new Node("node8", NodeType.RECREATION_CENTER);
        Node node9 = new Node("node9", NodeType.RECREATION_CENTER);
        Node node10 = new Node("node10", NodeType.RECREATION_CENTER);
        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2, node3, node4, node5, node6, node7, node8, node9, node10));

        float result = graphService.getPercentageOfLocationType(NodeType.CITY);

        assertThat(result).isEqualTo(0.1f);
    }

    @Test
    void getPercentageOfLocationType_shouldReturn20Percent_whenGraphHas20PercentOfRestaurants() {

        Node node1 = new Node("node1", NodeType.RESTAURANT);
        Node node2 = new Node("node2", NodeType.RESTAURANT);
        Node node3 = new Node("node3", NodeType.RECREATION_CENTER);
        Node node4 = new Node("node4", NodeType.RECREATION_CENTER);
        Node node5 = new Node("node5", NodeType.RECREATION_CENTER);
        Node node6 = new Node("node6", NodeType.RECREATION_CENTER);
        Node node7 = new Node("node7", NodeType.RECREATION_CENTER);
        Node node8 = new Node("node8", NodeType.RECREATION_CENTER);
        Node node9 = new Node("node9", NodeType.RECREATION_CENTER);
        Node node10 = new Node("node10", NodeType.RECREATION_CENTER);
        when(nodeRepository.getAllNodes()).thenReturn(List.of(node1, node2, node3, node4, node5, node6, node7, node8, node9, node10));

        float result = graphService.getPercentageOfLocationType(NodeType.RESTAURANT);

        assertThat(result).isEqualTo(0.2f);
    }

    @Test
    void getPercentageOfLinkType_shouldReturn10Percent_whenGraphHas10PercentOfHighways() {

        Node node1 = new Node("node1", NodeType.CITY);
        Node node2 = new Node("node2", NodeType.RECREATION_CENTER);
        Link link1 = new Link("link1", node1, LinkType.HIGHWAY, 1);
        Link link2 = new Link("link2", node2, LinkType.DEPARTMENTAL, 1);
        Link link3 = new Link("link3", node2, LinkType.DEPARTMENTAL, 1);
        Link link4 = new Link("link4", node2, LinkType.DEPARTMENTAL, 1);
        Link link5 = new Link("link5", node2, LinkType.DEPARTMENTAL, 1);
        Link link6 = new Link("link6", node2, LinkType.DEPARTMENTAL, 1);
        Link link7 = new Link("link7", node2, LinkType.DEPARTMENTAL, 1);
        Link link8 = new Link("link8", node2, LinkType.DEPARTMENTAL, 1);
        Link link9 = new Link("link9", node2, LinkType.DEPARTMENTAL, 1);
        Link link10 = new Link("link10", node2, LinkType.DEPARTMENTAL, 1);
        when(linkRepository.getAllLinks()).thenReturn(List.of(link1, link2, link3, link4, link5, link6, link7, link8, link9, link10));

        float result = graphService.getPercentageOfLinkType(LinkType.HIGHWAY);

        assertThat(result).isEqualTo(0.1f);
    }

    @Test
    void getPercentageOfLinkType_shouldReturn20Percent_whenGraphHas20PercentOfNationals() {

        Node node1 = new Node("node1", NodeType.CITY);
        Node node2 = new Node("node2", NodeType.RECREATION_CENTER);
        Link link1 = new Link("link1", node1, LinkType.NATIONAL, 1);
        Link link2 = new Link("link2", node2, LinkType.NATIONAL, 1);
        Link link3 = new Link("link3", node2, LinkType.DEPARTMENTAL, 1);
        Link link4 = new Link("link4", node2, LinkType.DEPARTMENTAL, 1);
        Link link5 = new Link("link5", node2, LinkType.DEPARTMENTAL, 1);
        Link link6 = new Link("link6", node2, LinkType.DEPARTMENTAL, 1);
        Link link7 = new Link("link7", node2, LinkType.DEPARTMENTAL, 1);
        Link link8 = new Link("link8", node2, LinkType.DEPARTMENTAL, 1);
        Link link9 = new Link("link9", node2, LinkType.DEPARTMENTAL, 1);
        Link link10 = new Link("link10", node2, LinkType.DEPARTMENTAL, 1);
        when(linkRepository.getAllLinks()).thenReturn(List.of(link1, link2, link3, link4, link5, link6, link7, link8, link9, link10));

        float result = graphService.getPercentageOfLinkType(LinkType.NATIONAL);

        assertThat(result).isEqualTo(0.2f);
    }

    @Test
    void hasEncounteredError_shouldReturnTrue_whenGraphHasEncounteredError() {

        when(nodeRepository.hasEncounteredError()).thenReturn(true);

        boolean result = graphService.hasEncounteredError();

        assertThat(result).isTrue();
    }

    @Test
    void hasEncounteredError_shouldReturnFalse_whenGraphHasNotEncounteredError() {

        when(nodeRepository.hasEncounteredError()).thenReturn(false);

        boolean result = graphService.hasEncounteredError();

        assertThat(result).isFalse();
    }

    @Test
    void getGraphCharacteristics_shouldReturnCorrectGraphCharacteristics() {

        GraphService mock = mock(GraphService.class);
        when(mock.countCities()).thenReturn(1);
        when(mock.countRoads()).thenReturn(2);
        when(mock.getPercentageOfLinkType(LinkType.HIGHWAY)).thenReturn(0.1f);
        when(mock.getPercentageOfLinkType(LinkType.NATIONAL)).thenReturn(0.2f);
        when(mock.getPercentageOfLinkType(LinkType.DEPARTMENTAL)).thenReturn(0.7f);
        when(mock.getPercentageOfLocationType(NodeType.CITY)).thenReturn(0.1f);
        when(mock.getPercentageOfLocationType(NodeType.RECREATION_CENTER)).thenReturn(0.2f);
        when(mock.getPercentageOfLocationType(NodeType.RESTAURANT)).thenReturn(0.7f);
        when(mock.hasEncounteredError()).thenReturn(false);
        when(mock.getGraphCharacteristics()).thenCallRealMethod();
        GraphCharacteristics expected = aGraphCharacteristics()
                .withCityCount(1)
                .withRoadCount(2)
                .withCityPercentage(0.1f)
                .withRecreationPercentage(0.2f)
                .withRestaurantPercentage(0.7f)
                .withHighwayPercentage(0.1f)
                .withNationalPercentage(0.2f)
                .withDepartementalPercentage(0.7f)
                .withError(false)
                .build();

        GraphCharacteristics result = mock.getGraphCharacteristics();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getGraphCharacteristics_shouldReturnCorrectGraphCharacteristic2() {

        GraphService mock = mock(GraphService.class);
        when(mock.countCities()).thenReturn(3);
        when(mock.countRoads()).thenReturn(1);
        when(mock.getPercentageOfLinkType(LinkType.HIGHWAY)).thenReturn(0.3f);
        when(mock.getPercentageOfLinkType(LinkType.NATIONAL)).thenReturn(0.3f);
        when(mock.getPercentageOfLinkType(LinkType.DEPARTMENTAL)).thenReturn(0.4f);
        when(mock.getPercentageOfLocationType(NodeType.CITY)).thenReturn(0.4f);
        when(mock.getPercentageOfLocationType(NodeType.RECREATION_CENTER)).thenReturn(0.4f);
        when(mock.getPercentageOfLocationType(NodeType.RESTAURANT)).thenReturn(0.2f);
        when(mock.hasEncounteredError()).thenReturn(true);
        when(mock.getGraphCharacteristics()).thenCallRealMethod();
        GraphCharacteristics expected = aGraphCharacteristics()
                .withCityCount(3)
                .withRoadCount(1)
                .withCityPercentage(0.4f)
                .withRecreationPercentage(0.4f)
                .withRestaurantPercentage(0.2f)
                .withHighwayPercentage(0.3f)
                .withNationalPercentage(0.3f)
                .withDepartementalPercentage(0.4f)
                .withError(true)
                .build();

        GraphCharacteristics result = mock.getGraphCharacteristics();

        assertThat(result).isEqualTo(expected);
    }

}
