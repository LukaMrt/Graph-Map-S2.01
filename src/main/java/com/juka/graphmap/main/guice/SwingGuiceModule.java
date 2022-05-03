package com.juka.graphmap.main.guice;

import com.google.inject.AbstractModule;
import com.juka.graphmap.domain.application.graph.GraphLoader;
import com.juka.graphmap.domain.application.graph.LinkRepository;
import com.juka.graphmap.domain.application.graph.NodeRepository;
import com.juka.graphmap.domain.model.file.FilePath;
import com.juka.graphmap.infrastructure.DefaultLinkRepository;
import com.juka.graphmap.infrastructure.DefaultNodeRepository;
import com.juka.graphmap.infrastructure.graph.FileGraphLoader;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.graph.SwingGraphUI;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.home.SwingHomeUI;
import com.juka.graphmap.view.fram.GraphMapJFrame;
import com.juka.graphmap.view.home.SwingHomeView;

import javax.swing.*;

public class SwingGuiceModule extends AbstractModule {

    private final FilePath path;

    public SwingGuiceModule(String path) {
        this.path = new FilePath(path);
    }

    @Override
    protected void configure() {

        bind(FilePath.class).toInstance(path);

        NodeRepository nodeRepository = new DefaultNodeRepository();
        LinkRepository linkRepository = new DefaultLinkRepository();
        JFrame frame = new GraphMapJFrame();

        bind(JFrame.class).toInstance(frame);

        bind(NodeRepository.class).toInstance(nodeRepository);
        bind(LinkRepository.class).toInstance(linkRepository);
        bind(GraphLoader.class).to(FileGraphLoader.class);

        bind(HomeView.class).to(SwingHomeView.class);

        bind(HomeUI.class).to(SwingHomeUI.class);
        bind(GraphUI.class).to(SwingGraphUI.class);

    }

}
