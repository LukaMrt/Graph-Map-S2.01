package com.juka.graphmap.domain.infrastructure;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.infrastructure.DefaultLinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultLinkRepositoryTest {

    private DefaultLinkRepository linkRepository;

    @BeforeEach
    void setUp() {
        linkRepository = new DefaultLinkRepository();
    }

    @Test
    void getAllLinks_shouldReturnEmptyList_whenNoLinkAdded() {

        List<Link> links = linkRepository.getAllLinks();

        assertThat(links).isEmpty();
    }

    @Test
    void getAllLinks_shouldReturnListWithOneLink_whenOneLinkAdded() {

        Node node1 = new Node("node1", NodeType.CITY);
        Link link = new Link("node", node1, LinkType.HIGHWAY, 10);
        linkRepository.addLink(link);

        List<Link> links = linkRepository.getAllLinks();

        assertThat(links).containsExactly(link);
    }

    @Test
    void getLink_shouldReturnLink_whenLinkAdded() {

        Node node1 = new Node("node1", NodeType.CITY);
        Link link = new Link("node", node1, LinkType.HIGHWAY, 10);
        linkRepository.addLink(link);

        Link result = linkRepository.getLink("node");

        assertThat(result).isEqualTo(link);
    }

    @Test
    void getLink_shouldReturnNull_whenLinkNotAdded() {

        Link result = linkRepository.getLink("node");

        assertThat(result).isNull();
    }

}
