package com.juka.graphmap.infrastructure.graph;

import com.juka.graphmap.domain.application.graph.GraphLoader;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.exception.*;
import com.juka.graphmap.domain.model.file.FilePath;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GraphLoader from a file.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class FileGraphLoader implements GraphLoader {

    private final FilePath filePath;

    /**
     * Constructor of the FileGraphLoader.
     *
     * @param filePath the path of the file to load
     */
    @Inject
    public FileGraphLoader(FilePath filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Node> loadNodes() {

        List<Node> nodes = new ArrayList<>();

        try {
            loadNodes(nodes);
        } catch (Exception e) {
            System.out.println("Fichier non trouvé");
            return null;
        }

        return nodes;
    }

    /**
     * Load all the nodes from the file.
     *
     * @param nodes the list of nodes to fill
     * @throws Exception if the file doesn't exist of if the file format is incorrect
     */
    private void loadNodes(List<Node> nodes) throws Exception {

        File file = new File(filePath.path());

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        BufferedReader reader = new BufferedReader(new FileReader(filePath.path()));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] node = line
                    .replace("\n", "")
                    .split(":")[0]
                    .split(",");

            if (node.length != 4) {
                throw new InvalidNodeFormatException();
            }

            nodes.add(new Node(node[1], NodeType.of(node[0]), Integer.parseInt(node[2]), Integer.parseInt(node[3])));
        }

        reader.close();
    }


    @Override
    public List<Link> loadLinks(NodeRepository nodeRepository) {

        List<Link> links = new ArrayList<>();

        try {
            loadLinks(nodeRepository, links);
        } catch (Exception e) {
            System.out.println("Fichier non trouvé");
            return null;
        }

        return links;
    }

    /**
     * Load all the links from the file.
     *
     * @param nodeRepository        the repository of nodes
     * @param links                 the list of links to fill
     * @throws IOException          if the file doesn't exist of if the file format is incorrect
     * @throws RuntimeException     if the node doesn't exist
     */
    private void loadLinks(NodeRepository nodeRepository, List<Link> links) throws IOException, RuntimeException {

        File file = new File(filePath.path());

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        BufferedReader reader = new BufferedReader(new FileReader(filePath.path()));

        String line;
        String[] neighbors;
        String[] road;
        int doublePoint;
        Link link;

        while ((line = reader.readLine()) != null) {

            line = line.replace("\n", "");
            doublePoint = line.indexOf(":");

            if (doublePoint == -1) {
                continue;
            }

            String node = line.substring(0, doublePoint).split(",")[1];
            neighbors = line.substring(doublePoint + 1).split(";");

            for (String neighbor : neighbors) {

                road = neighbor.split(",");

                if (road.length != 4) {
                    throw new InvalidLinkFormatException();
                }

                Node origin = nodeRepository.getNode(node);
                Node destination = nodeRepository.getNode(road[3]);

                if (origin == null) {
                    throw new NodeNotFountException(node);
                }

                if (destination == null) {
                    throw new NodeNotFountException(road[3]);
                }

                if (LinkType.of(road[0]) == null) {
                    throw new InvalidLinkTypeException();
                }

                String[] finalRoad = road;
                if (origin.getNeighborsLinks().stream().map(Link::getName).anyMatch(name -> name.equals(finalRoad[1] + ".1"))) {
                    throw new LinkAlreadyExistException(finalRoad[1]);
                }

                String finalName = finalRoad[1] + ".1";
                if (links.stream().map(Link::getName).anyMatch(name -> name.equals(finalRoad[1] + ".1"))) {
                    finalName = finalRoad[1] + ".2";
                }

                link = new Link(finalName, destination, LinkType.of(road[0]), Integer.parseInt(road[2]));
                origin.addLink(link);
                links.add(link);

            }

        }

    }

}
