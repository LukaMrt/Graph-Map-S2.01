package com.juka.graphmap.main.guice;

import com.google.inject.AbstractModule;
import com.juka.graphmap.domain.application.graph.GraphLoader;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.path.FloydWarshallDistancesRepository;
import com.juka.graphmap.domain.model.file.FilePath;
import com.juka.graphmap.infrastructure.DefaultLinkRepository;
import com.juka.graphmap.infrastructure.DefaultNodeRepository;
import com.juka.graphmap.infrastructure.graph.FileGraphLoader;
import com.juka.graphmap.infrastructure.path.DefaultFloydWarshallDistancesRepository;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.ui.compare.TerminalCompareUI;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.graph.GraphView;
import com.juka.graphmap.ui.graph.TerminalGraphUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.home.TerminalHomeUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsView;
import com.juka.graphmap.ui.neighbours.direct.TerminalDirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsView;
import com.juka.graphmap.ui.neighbours.indirect.TerminalIndirectNeighborsUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.ui.path.PathView;
import com.juka.graphmap.ui.path.TerminalPathUI;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.ui.roads.RoadsView;
import com.juka.graphmap.ui.roads.TerminalRoadsUI;
import com.juka.graphmap.view.compare.TerminalCompareView;
import com.juka.graphmap.view.graph.TerminalGraphView;
import com.juka.graphmap.view.home.TerminalHomeView;
import com.juka.graphmap.view.neighbours.direct.TerminalDirectNeighborsView;
import com.juka.graphmap.view.neighbours.indirect.TerminalIndirectNeighborsView;
import com.juka.graphmap.view.path.TerminalPathView;
import com.juka.graphmap.view.roads.TerminalRoadsView;

import javax.inject.Singleton;

/**
 * Guice module for terminal application.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
public class TerminalGuiceModule extends AbstractModule {

    private final FilePath path;

    /**
     * Constructor of the terminal module.
     *
     * @param path path to the graph file
     */
    public TerminalGuiceModule(String path) {
        this.path = new FilePath(path);
    }

    @Override
    protected void configure() {

        bind(FilePath.class).toInstance(path);

        bind(NodeRepository.class).to(DefaultNodeRepository.class).in(Singleton.class);
        bind(LinkRepository.class).to(DefaultLinkRepository.class).in(Singleton.class);
        bind(FloydWarshallDistancesRepository.class).to(DefaultFloydWarshallDistancesRepository.class);
        bind(GraphLoader.class).to(FileGraphLoader.class);

        bind(HomeView.class).to(TerminalHomeView.class);
        bind(RoadsView.class).to(TerminalRoadsView.class);
        bind(GraphView.class).to(TerminalGraphView.class);
        bind(CompareView.class).to(TerminalCompareView.class);
        bind(PathView.class).to(TerminalPathView.class);
        bind(DirectNeighborsView.class).to(TerminalDirectNeighborsView.class);
        bind(IndirectNeighborsView.class).to(TerminalIndirectNeighborsView.class);

        bind(GraphUI.class).to(TerminalGraphUI.class);
        bind(RoadsUI.class).to(TerminalRoadsUI.class);
        bind(HomeUI.class).to(TerminalHomeUI.class);
        bind(CompareUI.class).to(TerminalCompareUI.class);
        bind(PathUI.class).to(TerminalPathUI.class);
        bind(DirectNeighborsUI.class).to(TerminalDirectNeighborsUI.class);
        bind(IndirectNeighborsUI.class).to(TerminalIndirectNeighborsUI.class);

    }

}
