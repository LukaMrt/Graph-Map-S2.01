package com.juka.graphmap.main.guice;

import com.google.inject.AbstractModule;
import com.juka.graphmap.domain.application.graph.GraphLoader;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.file.FilePath;
import com.juka.graphmap.infrastructure.DefaultLinkRepository;
import com.juka.graphmap.infrastructure.DefaultNodeRepository;
import com.juka.graphmap.infrastructure.graph.FileGraphLoader;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.ui.compare.TerminalCompareUI;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.graph.GraphView;
import com.juka.graphmap.ui.graph.TerminalGraphUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.home.TerminalHomeUI;
import com.juka.graphmap.ui.neighbours.DirectNeighboursUI;
import com.juka.graphmap.ui.neighbours.DirectNeighboursView;
import com.juka.graphmap.ui.neighbours.TerminalDirectNeighboursUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.ui.path.PathView;
import com.juka.graphmap.ui.path.TerminalPathUI;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.ui.roads.RoadsView;
import com.juka.graphmap.ui.roads.TerminalRoadsUI;
import com.juka.graphmap.view.compare.TerminalCompareView;
import com.juka.graphmap.view.graph.TerminalGraphView;
import com.juka.graphmap.view.home.TerminalHomeView;
import com.juka.graphmap.view.neighbours.TerminalDirectNeighboursView;
import com.juka.graphmap.view.path.TerminalPathView;
import com.juka.graphmap.view.roads.TerminalRoadsView;

public class TerminalGuiceModule extends AbstractModule {

    private final FilePath path;

    public TerminalGuiceModule(String path) {
        this.path = new FilePath(path);
    }

    @Override
    protected void configure() {

        bind(FilePath.class).toInstance(path);

        NodeRepository nodeRepository = new DefaultNodeRepository();
        LinkRepository linkRepository = new DefaultLinkRepository();

        bind(NodeRepository.class).toInstance(nodeRepository);
        bind(LinkRepository.class).toInstance(linkRepository);
        bind(GraphLoader.class).to(FileGraphLoader.class);

        bind(HomeView.class).to(TerminalHomeView.class);
        bind(RoadsView.class).to(TerminalRoadsView.class);
        bind(GraphView.class).to(TerminalGraphView.class);
        bind(CompareView.class).to(TerminalCompareView.class);
        bind(PathView.class).to(TerminalPathView.class);
        bind(DirectNeighboursView.class).to(TerminalDirectNeighboursView.class);

        bind(GraphUI.class).to(TerminalGraphUI.class);
        bind(RoadsUI.class).to(TerminalRoadsUI.class);
        bind(HomeUI.class).to(TerminalHomeUI.class);
        bind(CompareUI.class).to(TerminalCompareUI.class);
        bind(PathUI.class).to(TerminalPathUI.class);
        bind(DirectNeighboursUI.class).to(TerminalDirectNeighboursUI.class);

    }

}
