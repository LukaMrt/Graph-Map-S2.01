package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.link.Link;

import java.util.List;

public interface LinkRepository {
    
    List<Link> getAllLinks();
    
    Link getLink(String name);
    
}