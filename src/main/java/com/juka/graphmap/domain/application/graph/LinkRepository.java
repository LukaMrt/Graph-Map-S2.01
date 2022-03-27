package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.link.Link;

import java.util.List;

public interface LinkRepository {

    void addLink(Link link);
    
    List<Link> getAllLinks();
    
    Link getLink(String name);
    
}