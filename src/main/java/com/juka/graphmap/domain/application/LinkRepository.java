package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Link;

import java.util.List;

public interface LinkRepository {
    
    List<Link> getAllLinks();
    
    Link getLink(String name);
    
}