package com.juka.graphmap.main.guice;

import com.google.inject.AbstractModule;
import com.juka.graphmap.domain.application.graph.GraphLoader;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.application.path.FloydWarshallDistancesRepository;
import com.juka.graphmap.domain.application.path.PathService;
import com.juka.graphmap.domain.application.path.RoadsFinderService;
import com.juka.graphmap.domain.model.file.FilePath;
import com.juka.graphmap.infrastructure.DefaultLinkRepository;
import com.juka.graphmap.infrastructure.DefaultNodeRepository;
import com.juka.graphmap.infrastructure.graph.FileGraphLoader;
import com.juka.graphmap.infrastructure.path.DefaultFloydWarshallDistancesRepository;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.compare.CompareView;
import com.juka.graphmap.ui.compare.SwingCompareUI;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.graph.GraphView;
import com.juka.graphmap.ui.graph.SwingGraphUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.home.SwingHomeUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsView;
import com.juka.graphmap.ui.neighbours.direct.SwingDirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsView;
import com.juka.graphmap.ui.neighbours.indirect.SwingIndirectNeighborsUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.ui.path.PathView;
import com.juka.graphmap.ui.path.SwingPathUI;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.ui.roads.RoadsView;
import com.juka.graphmap.ui.roads.SwingRoadsUI;
import com.juka.graphmap.view.compare.NewSwingCompareView;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.graph.NewSwingGraphView;
import com.juka.graphmap.view.home.NewSwingHomeView;
import com.juka.graphmap.view.neighbours.direct.NewSwingDirectNeighborsView;
import com.juka.graphmap.view.neighbours.indirect.NewSwingIndirectNeighborsView;
import com.juka.graphmap.view.path.NewSwingPathView;
import com.juka.graphmap.view.roads.NewSwingRoadsView;

import javax.inject.Singleton;
import javax.swing.*;

public class NewSwingGuiceModule extends AbstractModule {

    private final FilePath path;

    public NewSwingGuiceModule(String path) {
        this.path = new FilePath(path);
    }

    @Override
    protected void configure() {

        bind(FilePath.class).toInstance(path);

        bind(JFrame.class).to(GraphMapJFrame.class).in(Singleton.class);

        bind(NodeRepository.class).to(DefaultNodeRepository.class).in(Singleton.class);
        bind(LinkRepository.class).to(DefaultLinkRepository.class).in(Singleton.class);
        bind(FloydWarshallDistancesRepository.class).to(DefaultFloydWarshallDistancesRepository.class).in(Singleton.class);
        bind(GraphLoader.class).to(FileGraphLoader.class);
        bind(RoadsFinderService.class).to(PathService.class);

        bind(HomeView.class).to(NewSwingHomeView.class);
        bind(GraphView.class).to(NewSwingGraphView.class);
        bind(CompareView.class).to(NewSwingCompareView.class);
        bind(DirectNeighborsView.class).to(NewSwingDirectNeighborsView.class);
        bind(IndirectNeighborsView.class).to(NewSwingIndirectNeighborsView.class);
        bind(PathView.class).to(NewSwingPathView.class);
        bind(RoadsView.class).to(NewSwingRoadsView.class);

        bind(HomeUI.class).to(SwingHomeUI.class);
        bind(GraphUI.class).to(SwingGraphUI.class);
        bind(CompareUI.class).to(SwingCompareUI.class);
        bind(DirectNeighborsUI.class).to(SwingDirectNeighborsUI.class);
        bind(IndirectNeighborsUI.class).to(SwingIndirectNeighborsUI.class);
        bind(PathUI.class).to(SwingPathUI.class);
        bind(RoadsUI.class).to(SwingRoadsUI.class);

    }

}
