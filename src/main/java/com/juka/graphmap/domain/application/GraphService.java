package com.juka.graphmap.domain.application;

import com.juka.graphmap.domain.model.*;

import static com.juka.graphmap.domain.model.GraphCharacteristicsBuilder.aGraphCharacteristics;

public class GraphService {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;

    public GraphService(NodeRepository nodeRepository, LinkRepository linkRepository) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
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
        return linkRepository.getAllLinks().size();
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

}
