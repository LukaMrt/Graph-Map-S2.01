package com.juka.graphmap.domain.application.link;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.exception.LinkDoesntExistException;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class LinkService {

    private final LinkRepository linkRepository;
    private final NodeRepository nodeRepository;

    @Inject
    public LinkService(NodeRepository nodeRepository, LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
        this.nodeRepository = nodeRepository;
    }

    public Link getLink(String name) {
        return linkRepository.getLink(name);
    }

    public int getDistance(String node1, String node2) throws LinkDoesntExistException {
        Optional<Link> neighbor = nodeRepository.getNode(node1)
                .getNeighborsLinks()
                .stream()
                .filter(link -> link.getDestination().getName().equals(node2))
                .findFirst();

        if (neighbor.isEmpty()) {
            throw new LinkDoesntExistException();
        }

        return neighbor.get().getDistance();
    }

    public LinkCharacteristics getLinkCharacteristics(String link) {

        List<Node> nodes = linkRepository.getAllLinks().stream()
                .filter(l -> l.getName().contains(link + "."))
                .map(Link::getDestination)
                .toList();

        if (nodes.size() != 2) {
            return new LinkCharacteristics("", "", "", 0);
        }

        return new LinkCharacteristics(getLink(link + ".1").getRoadName(), nodes.get(0).getName(), nodes.get(1).getName(), getLink(link + ".1").getDistance());
    }

    public boolean linkExist(String entry) {
        return linkRepository.getLink(entry + ".1") != null;
    }

}
