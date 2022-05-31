package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.link.Link;

import java.util.List;

/**
 * Interface of a link repository.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public interface LinkRepository {

    /**
     * Add a link to the repository.
     * @param link The link to add.
     */
    void addLink(Link link);

    /**
     * Returns all the links in the repository.
     * @return The list of links.
     */
    List<Link> getAllLinks();

    /**
     * Returns the link with the given name.
     * @param name The name of the link.
     * @return The link with the given name.
     */
    Link getLink(String name);
    
}