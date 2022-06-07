package com.juka.graphmap.domain.application.link;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to get Link characteristics.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class LinkService {

    private final LinkRepository linkRepository;

    /**
     * Constructor of the LinkService.
     *
     * @param linkRepository LinkRepository to get Link characteristics.
     */
    @Inject
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    /**
     * Get a link with a given name.
     *
     * @param name Name of the link.
     * @return Link with the given name.
     */
    public Link getLink(String name) {
        return linkRepository.getLink(name);
    }

    /**
     * Returns the link characteristics of a given link name.
     *
     * @param linkName Name of the link.
     * @return Link characteristics of the given link name.
     */
    public LinkCharacteristics getLinkCharacteristics(String linkName) {

        List<Node> nodes = linkRepository.getAllLinks().stream()
                .filter(l -> l.getName().contains(linkName + "."))
                .map(Link::getDestination)
                .sorted()
                .collect(Collectors.toList());

        if (nodes.size() != 2) {
            return LinkCharacteristics.empty();
        }

        Link link = getLink(linkName + ".1");
        return new LinkCharacteristics(link.getRoadNameWithIndex(), nodes.get(0).getName(), nodes.get(1).getName(), link.getType().toString(), link.getDistance());
    }

    /**
     * Sets all the links to unselected.
     */
    public void unselectAll() {
        linkRepository.getAllLinks().forEach(Link::unselect);
    }

    /**
     * Sets the link with the given name to selected.
     *
     * @param link The link to select.
     */
    public void select(String link) {
        linkRepository.getAllLinks().stream()
                .filter(l -> l.getName().equals(link + ".1") || l.getName().equals(link + ".2"))
                .forEach(Link::select);
    }

}
