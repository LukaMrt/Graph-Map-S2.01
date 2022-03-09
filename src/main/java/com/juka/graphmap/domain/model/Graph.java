package com.juka.graphmap.domain.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luka Maret
 * @since 0.1.0
 *
 * Graph class represents a graph.
 */
public class Graph {

    private final Map<String, Node> nodes = new HashMap<>();
    private final Map<String, Link> links = new HashMap<>();

}
