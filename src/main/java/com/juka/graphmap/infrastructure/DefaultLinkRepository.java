package com.juka.graphmap.infrastructure;

import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.model.link.Link;

import java.util.ArrayList;
import java.util.List;

public class DefaultLinkRepository implements LinkRepository {

    private final List<Link> links = new ArrayList<>();

    @Override
    public void addLink(Link link) {
        links.add(link);
    }

    @Override
    public List<Link> getAllLinks() {
        return new ArrayList<>(links);
    }

    @Override
    public Link getLink(String name) {
        return links.stream()
                .filter(link -> link.getName().startsWith(name))
                .findFirst()
                .orElse(null);
    }

}
