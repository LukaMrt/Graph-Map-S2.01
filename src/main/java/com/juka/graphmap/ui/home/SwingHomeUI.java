package com.juka.graphmap.ui.home;

import com.juka.graphmap.domain.application.graph.GraphService;
import com.juka.graphmap.domain.model.graph.GraphCharacteristics;

import javax.inject.Inject;

public class SwingHomeUI implements HomeUI {

    private final GraphService graphService;
    private final HomeView view;

    @Inject
    public SwingHomeUI(GraphService graphService, HomeView view) {
        this.graphService = graphService;
        this.view = view;
    }

    @Override
    public void interact() {

        GraphCharacteristics characteristics = graphService.getGraphCharacteristics();
        view.display(characteristics, graphService.getAllNodes());

    }

}
