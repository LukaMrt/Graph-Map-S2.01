package com.juka.graphmap.domain.application.link;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.exceptions.LinkDoesntExist;
import com.juka.graphmap.domain.model.link.Link;

import java.util.Optional;

public class LinkService {

    private final LinkRepository linkRepository;
    private final NodeRepository nodeRepository;

    public LinkService(NodeRepository nodeRepository, LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
        this.nodeRepository = nodeRepository;
    }

    public Link getLink(String name) {
        return linkRepository.getLink(name);
    }

    public int getDistance(String node1, String node2) throws LinkDoesntExist {
        Optional<Link> neighbor = nodeRepository.getNode(node1)
                .getNeighborsLinks()
                .stream()
                .filter(link -> link.getDestination().getName().equals(node2))
                .findFirst();

        if (neighbor.isEmpty()) {
            throw new LinkDoesntExist();
        }

        return neighbor.get().getDistance();
    }
    
}
