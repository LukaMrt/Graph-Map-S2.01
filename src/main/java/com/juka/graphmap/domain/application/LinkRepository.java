package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.Link;

public interface LinkRepository {
    Link getLink(String name);
}
