package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.link.LinkService;
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
public class LinkServiceTest {
    @Mock
    private NodeRepository nodeRepository;
    private LinkRepository linkRepository;
    private LinkService linkService;

    @BeforeEach
    public void setUp() {
        linkService = new LinkService(nodeRepository, linkRepository);
    }

    @Test
    public void getDistance_shouldReturn10_whenTwoNodesHaveALinkWithDistanceAt10() throws Exception {
        Node node1 = new Node("Start", NodeType.CITY);
        Node node2 = new Node("End", NodeType.CITY);

        Link link = new Link("Middle", node2, LinkType.DEPARTMENTAL, 10);

        node1.addLink(link);

        when(nodeRepository.getNode("Start")).thenReturn(node1);

        assertThat(linkService.getDistance("Start", "End")).isEqualTo(10);
    }

    @Test
    public void getDistance_shouldReturn20_whenTwoNodesHaveALinkWithDistanceAt20() throws Exception {
        Node node1 = new Node("Start", NodeType.CITY);
        Node node2 = new Node("End", NodeType.CITY);

        Link link = new Link("Middle", node2, LinkType.DEPARTMENTAL, 20);

        node1.addLink(link);

        when(nodeRepository.getNode("Start")).thenReturn(node1);

        assertThat(linkService.getDistance("Start", "End")).isEqualTo(20);
    }

}
