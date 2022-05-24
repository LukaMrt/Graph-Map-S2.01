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

    @Inject
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link getLink(String name) {
        return linkRepository.getLink(name);
    }

    public LinkCharacteristics getLinkCharacteristics(String linkName) {

        List<Node> nodes = linkRepository.getAllLinks().stream()
                .filter(l -> l.getName().contains(linkName + "."))
                .map(Link::getDestination)
                .sorted()
                .toList();

        if (nodes.size() != 2) {
            return LinkCharacteristics.empty();
        }

        Link link = getLink(linkName + ".1");
        return new LinkCharacteristics(link.getRoadNameWithIndex(), nodes.get(0).getName(), nodes.get(1).getName(), link.getType().toString(), link.getDistance());
    }

}
