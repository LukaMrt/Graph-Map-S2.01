package com.juka.graphmap.infrastructure;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.model.link.Link;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Default implementation of LinkRepository with an ArrayList.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
@Singleton
public class DefaultLinkRepository implements LinkRepository {

    private final List<Link> links = new ArrayList<>();

    @Override
    public void addLink(Link link) {
        links.add(link);
    }

    @Override
    public List<Link> getAllLinks() {
        ArrayList<Link> links1 = new ArrayList<>(this.links);
        links1.sort(Comparator.comparing(Link::getRoadNameWithIndex));
        return links1;
    }

    @Override
    public Link getLink(String name) {
        return links.stream()
                .filter(link -> link.getName().startsWith(name))
                .findFirst()
                .orElse(null);
    }

}
