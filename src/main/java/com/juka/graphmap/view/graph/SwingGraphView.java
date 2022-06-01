package com.juka.graphmap.view.graph;

import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.compare.CompareUI;
import com.juka.graphmap.ui.graph.GraphView;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.SwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;

public class SwingGraphView extends SwingView implements GraphView {

    private final CompareUI compareUI;
    private final PathUI pathUI;
    private final DirectNeighborsUI directNeighborsUI;
    private final IndirectNeighborsUI indirectNeighborsUI;
    private final HomeUI homeUI;

    private List<String> nodes;
    private List<String> links;

    @Inject
    public SwingGraphView(GraphMapJFrame frame, CompareUI compareUI, PathUI pathUI, DirectNeighborsUI directNeighborsUI, IndirectNeighborsUI indirectNeighborsUI, HomeUI homeUI) {
        super(frame);
        this.compareUI = compareUI;
        this.pathUI = pathUI;
        this.directNeighborsUI = directNeighborsUI;
        this.indirectNeighborsUI = indirectNeighborsUI;
        this.homeUI = homeUI;
    }

    @Override
    public void display(List<Node> nodes, List<String> links) {
        this.nodes = nodes.stream().map(Node::getName).toList();
        this.links = links;
        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Aucune action n'est disponible sur cet écran.";
    }

    @Override
    protected Title getTitle() {
        return new Title("Graphe", 2);
    }

    @Override
    protected List<JButton> getButtons() {
        return List.of(
                aButton().withText("Comparaison")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> compareUI.interact(null, null))
                        .build(),
                aButton().withText("Chemin")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> pathUI.interact(null, null))
                        .build(),
                aButton().withText("Retour")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> homeUI.interact())
                        .build(),
                aButton().withText("Description")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> directNeighborsUI.interact(null, null))
                        .build(),
                aButton().withText("2-distance")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> indirectNeighborsUI.interact(null, null))
                        .build()
        );
    }

    @Override
    protected JPanel buildLeftPanel() {
        return buildPanel(nodes, "Liste des noeuds :");
    }

    @Override
    protected JPanel buildRightPanel() {
        return buildPanel(links, "Liste des liens :");
    }

    private JPanel buildPanel(List<String> data, String title) {
        JScrollPane scrollPane = anHorizontalList()
                .withData(data)
                .withSize(200, 400)
                .withSingleSelection()
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal()
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addVerticalGlue()
                .add(aLabel().withText(title).isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(scrollPane)
                .addVerticalGlue()
                .build();
    }

}
