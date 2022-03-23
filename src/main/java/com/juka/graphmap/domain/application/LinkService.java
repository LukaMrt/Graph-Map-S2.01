package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Link;

import java.util.List;

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

    public int getDistance(String node1, String node2) throws Exception{
        List<Link> neighbors = nodeRepository.getNode(node1).getNeighborsLinks();
        int i = 0;
        while (i < neighbors.size() && !neighbors.get(i).getDestination().getName().equals(node2)) i++;
        if (i == neighbors.size()) {
            throw new Exception("Link doesn't exist");
        } else {
            return neighbors.get(i).getDistance();
        }
    }
}
