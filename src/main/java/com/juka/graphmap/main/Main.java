package com.juka.graphmap.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
import com.juka.graphmap.main.guice.TerminalGuiceModule;
import com.juka.graphmap.ui.home.HomeUI;

public class Main {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new TerminalGuiceModule());

        NodeRepository nodeRepository = injector.getInstance(NodeRepository.class);
        LinkRepository linkRepository = injector.getInstance(LinkRepository.class);

        Node node1 = new Node("node1", NodeType.CITY);
        Node node2 = new Node("node2", NodeType.RESTAURANT);
        Node node3 = new Node("node3", NodeType.RESTAURANT);
        Node node4 = new Node("node4", NodeType.CITY);
        Node node5 = new Node("node5", NodeType.RECREATION_CENTER);
        Node node6 = new Node("node6", NodeType.CITY);
        Node node7 = new Node("node7", NodeType.RECREATION_CENTER);

        Link link1 = new Link("link1", node1, LinkType.HIGHWAY, 100);
        Link link2 = new Link("link2", node2, LinkType.DEPARTMENTAL, 324);
        Link link3 = new Link("link3", node3, LinkType.NATIONAL, 3);
        Link link4 = new Link("link4", node4, LinkType.DEPARTMENTAL, 10);
        Link link5 = new Link("link5", node5, LinkType.HIGHWAY, 100);
        Link link6 = new Link("link6", node6, LinkType.DEPARTMENTAL, 2348);
        Link link7 = new Link("link7", node7, LinkType.NATIONAL, 3);
        Link link8 = new Link("link1", node1, LinkType.HIGHWAY, 43);
        Link link9 = new Link("link2", node2, LinkType.DEPARTMENTAL, 100);
        Link link10 = new Link("link3", node3, LinkType.HIGHWAY, 23);
        Link link11 = new Link("link4", node4, LinkType.NATIONAL, 45);
        Link link12 = new Link("link5", node5, LinkType.NATIONAL, 5);
        Link link13 = new Link("link6", node6, LinkType.NATIONAL, 67);
        Link link14 = new Link("link7", node7, LinkType.HIGHWAY, 3);

        node1.addLink(link2);
        node1.addLink(link5);
        node2.addLink(link1);
        node2.addLink(link3);
        node3.addLink(link2);
        node3.addLink(link4);
        node4.addLink(link3);
        node4.addLink(link6);
        node5.addLink(link5);
        node5.addLink(link7);
        node6.addLink(link6);
        node6.addLink(link8);
        node7.addLink(link7);
        node7.addLink(link9);
        node3.addLink(link10);
        node3.addLink(link11);
        node3.addLink(link12);
        node3.addLink(link13);
        node3.addLink(link14);

        nodeRepository.addNode(node1);
        nodeRepository.addNode(node2);
        nodeRepository.addNode(node3);
        nodeRepository.addNode(node4);
        nodeRepository.addNode(node5);
        nodeRepository.addNode(node6);
        nodeRepository.addNode(node7);

        linkRepository.addLink(link1);
        linkRepository.addLink(link2);
        linkRepository.addLink(link3);
        linkRepository.addLink(link4);
        linkRepository.addLink(link5);
        linkRepository.addLink(link6);
        linkRepository.addLink(link7);
        linkRepository.addLink(link8);
        linkRepository.addLink(link9);
        linkRepository.addLink(link10);
        linkRepository.addLink(link11);
        linkRepository.addLink(link12);
        linkRepository.addLink(link13);
        linkRepository.addLink(link14);

        injector.getInstance(HomeUI.class).interact();

    }

}
