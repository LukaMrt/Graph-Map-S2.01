package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;

import java.util.List;

import static com.juka.graphmap.domain.model.graph.GraphCharacteristicsBuilder.aGraphCharacteristics;

public class GraphService {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;
    private final GraphLoader graphLoader;

    @Inject
    public GraphService(NodeRepository nodeRepository, LinkRepository linkRepository, GraphLoader graphLoader) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
        this.graphLoader = graphLoader;
    }

    public GraphCharacteristics getGraphCharacteristics() {
        return aGraphCharacteristics()
                .withCityCount(countCities())
                .withRoadCount(countRoads())
                .withCityPercentage(getPercentageOfLocationType(NodeType.CITY))
                .withRestaurantPercentage(getPercentageOfLocationType(NodeType.RESTAURANT))
                .withRecreationPercentage(getPercentageOfLocationType(NodeType.RECREATION_CENTER))
                .withHighwayPercentage(getPercentageOfLinkType(LinkType.HIGHWAY))
                .withNationalPercentage(getPercentageOfLinkType(LinkType.NATIONAL))
                .withDepartementalPercentage(getPercentageOfLinkType(LinkType.DEPARTMENTAL))
                .withError(hasEncounteredError())
                .build();
    }

    public int countCities() {
        return nodeRepository.getAllNodes().size();
    }

    public int countRoads() {
        return linkRepository.getAllLinks().size() / 2;
    }

    public float getPercentageOfLocationType(NodeType type) {

        int total = nodeRepository.getAllNodes().size();
        long ofType = nodeRepository.getAllNodes().stream()
                .map(Node::getType)
                .filter(type::equals)
                .count();

        return ofType / (float) total;
    }

    public float getPercentageOfLinkType(LinkType type) {

        int total = linkRepository.getAllLinks().size();
        long ofType = linkRepository.getAllLinks().stream()
                .map(Link::getType)
                .filter(type::equals)
                .count();

        return ofType / (float) total;
    }

    public boolean hasEncounteredError() {
        return nodeRepository.hasEncounteredError();
    }

    public void load() {
        List<Node> nodes = graphLoader.loadNodes();

        if (nodes == null) {
            nodeRepository.encounterError();
            return;
        }

        nodes.forEach(nodeRepository::addNode);
        List<Link> links = graphLoader.loadLinks(nodeRepository);

        if (links == null) {
            nodeRepository.encounterError();
            return;
        }

        links.forEach(linkRepository::addLink);
    }

}
