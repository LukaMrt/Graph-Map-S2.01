package com.juka.graphmap.domain.application.graph;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.util.List;

import static com.juka.graphmap.domain.model.graph.GraphCharacteristicsBuilder.aGraphCharacteristics;

/**
 * Service used to analyse the entire graph.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class GraphService {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;
    private final GraphLoader graphLoader;

    /**
     * Constructor of the service.
     *
     * @param nodeRepository The node repository.
     * @param linkRepository The link repository.
     * @param graphLoader    The graph loader.
     */
    @Inject
    public GraphService(NodeRepository nodeRepository, LinkRepository linkRepository, GraphLoader graphLoader) {
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
        this.graphLoader = graphLoader;
    }

    /**
     * Returns graph characteristics.
     *
     * @return The graph characteristics.
     */
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

    /**
     * Returns the number of cities in the graph.
     *
     * @return The number of cities in the graph.
     */
    public int countCities() {
        return nodeRepository.getAllNodes().size();
    }

    /**
     * Returns the number of roads in the graph.
     *
     * @return The number of roads in the graph.
     */
    public int countRoads() {
        return linkRepository.getAllLinks().size() / 2;
    }

    /**
     * Returns the percentage of nodes of the specified type.
     *
     * @param type The type of nodes.
     * @return The percentage of nodes of the specified type.
     */
    public float getPercentageOfLocationType(NodeType type) {

        int total = nodeRepository.getAllNodes().size();
        long ofType = nodeRepository.getAllNodes().stream()
                .map(Node::getType)
                .filter(type::equals)
                .count();

        return ofType / (float) total;
    }

    /**
     * Returns the percentage of links of the specified type.
     *
     * @param type The type of links.
     * @return The percentage of links of the specified type.
     */
    public float getPercentageOfLinkType(LinkType type) {

        int total = linkRepository.getAllLinks().size();
        long ofType = linkRepository.getAllLinks().stream()
                .map(Link::getType)
                .filter(type::equals)
                .count();

        return ofType / (float) total;
    }

    /**
     * Returns true if an error has been encountered during the loading of the graph.
     *
     * @return True if an error has been encountered during the loading of the graph.
     */
    public boolean hasEncounteredError() {
        return nodeRepository.hasEncounteredError();
    }

    /**
     * Loads the nodes and links from the graph loader.
     */
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

    /**
     * Returns all the nodes in the graph.
     *
     * @return All the nodes in the graph.
     */
    public List<Node> getAllNodes() {
        return nodeRepository.getAllNodes();
    }

    /**
     * Returns true if the graph contains the specified node, false otherwise.
     *
     * @param entry The name of the node to check.
     * @return True if the graph contains the specified node, false otherwise.
     */
    public boolean nodeExist(String entry) {
        return nodeRepository.getNode(entry) != null;
    }

    /**
     * Returns the link with the specified name.
     *
     * @param entry The name of the link.
     * @return True if the graph contains the specified link, false otherwise.
     */
    public boolean linkExist(String entry) {
        return linkRepository.getLink(entry + ".1") != null;
    }

    /**
     * Returns all the links in the graph.
     *
     * @return All the links in the graph.
     */
    public List<Link> getAllLinks() {
        return linkRepository.getAllLinks();
    }

}
