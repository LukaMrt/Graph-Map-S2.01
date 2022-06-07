package com.juka.graphmap.domain.application.link;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTest {

    @Mock
    private LinkRepository linkRepository;

    private LinkService linkService;

    @BeforeEach
    public void setUp() {
        linkService = new LinkService(linkRepository);
    }

    @Test
    void getLink_shouldReturnLink() {
        Link link = new Link("link", null, LinkType.DEPARTMENTAL, 20);

        when(linkRepository.getLink("link")).thenReturn(link);

        assertThat(linkService.getLink("link")).isEqualTo(new Link("link", null, LinkType.DEPARTMENTAL, 20));
    }

    @Test
    void getLink_shouldReturnLink2() {
        Link link = new Link("link2", null, LinkType.DEPARTMENTAL, 10);

        when(linkRepository.getLink("link2")).thenReturn(link);

        assertThat(linkService.getLink("link2")).isEqualTo(new Link("link2", null, LinkType.DEPARTMENTAL, 10));
    }

    @Test
    void getLinkCharacteristics_shouldReturnLinkCharacteristics_whenLinkExists() {
        Node node1 = new Node("node1", NodeType.CITY, 0, 0);
        Node node2 = new Node("node2", NodeType.CITY, 0, 0);

        Link link = new Link("link.1", node2, LinkType.DEPARTMENTAL, 20);
        Link link2 = new Link("link.2", node1, LinkType.DEPARTMENTAL, 20);

        node1.addLink(link);
        node2.addLink(link2);

        when(linkRepository.getAllLinks()).thenReturn(Arrays.asList(link, link2));
        when(linkRepository.getLink("link.1")).thenReturn(link);

        LinkCharacteristics result = linkService.getLinkCharacteristics("link");

        assertThat(result).isEqualTo(new LinkCharacteristics("link", "node1", "node2", LinkType.DEPARTMENTAL.toString(), 20));
    }

    @Test
    void getLinkCharacteristics_shouldReturnEmptyLinkCharacteristics_whenLinkDoesNotExists() {

        when(linkRepository.getAllLinks()).thenReturn(Collections.emptyList());

        LinkCharacteristics result = linkService.getLinkCharacteristics("link");

        assertThat(result).isEqualTo(new LinkCharacteristics("", "", "", "", 0));
    }

    @Test
    void unselectAll_shouldUnselectAllLinks_whenLinksAreSelected() {
        Link link = new Link("Link", null, LinkType.DEPARTMENTAL, 20);
        link.select();
        when(linkRepository.getAllLinks()).thenReturn(Arrays.asList(link));

        linkService.unselectAll();

        assertThat(link.isSelected()).isFalse();
    }

    @Test
    void select_shouldSelectLink_whenNodeExist() {
        Link link = new Link("Link.1.1", null, LinkType.DEPARTMENTAL, 20);
        Link link2 = new Link("Link.2.1", null, LinkType.DEPARTMENTAL, 20);
        when(linkRepository.getAllLinks()).thenReturn(Arrays.asList(link, link2));

        linkService.select("Link.1");

        assertThat(link.isSelected()).isTrue();
        assertThat(link2.isSelected()).isFalse();
    }

}
