package com.juka.graphmap.main.guice;

import com.google.inject.AbstractModule;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.infrastructure.DefaultLinkRepository;
import com.juka.graphmap.infrastructure.DefaultNodeRepository;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.graph.TerminalGraphUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.home.TerminalHomeUI;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.ui.roads.TerminalRoadsUI;
import com.juka.graphmap.view.home.TerminalHomeView;

public class TerminalGuiceModule extends AbstractModule {

    @Override
    protected void configure() {

        NodeRepository nodeRepository = new DefaultNodeRepository();
        LinkRepository linkRepository = new DefaultLinkRepository();

        bind(NodeRepository.class).toInstance(nodeRepository);
        bind(LinkRepository.class).toInstance(linkRepository);

        bind(HomeView.class).to(TerminalHomeView.class);

        bind(GraphUI.class).to(TerminalGraphUI.class);
        bind(RoadsUI.class).to(TerminalRoadsUI.class);
        bind(HomeUI.class).to(TerminalHomeUI.class);

    }

}
