package com.juka.graphmap.view.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.ui.path.PathView;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.GlobalSwingView;
import com.juka.graphmap.view.swing.components.ScrollPaneBuilder;

import javax.inject.Inject;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

public class NewSwingPathView extends GlobalSwingView implements PathView {

    private final GraphUI graphUI;
    private final PathUI pathUI;
    private List<String> nodes;
    private String node1;
    private String node2;
    private Path path;

    @Inject
    public NewSwingPathView(GraphMapJFrame frame, GraphUI graphUI, PathUI pathUI) {
        super(frame);
        this.graphUI = graphUI;
        this.pathUI = pathUI;
    }

    @Override
    public void display(List<Node> nodes, String node1, String node2, Path path) {
        this.nodes = nodes.stream().map(Node::getName).toList();
        this.node1 = node1;
        this.node2 = node2;
        this.path = path;
        super.display(nodes);
    }

    @Override
    protected Title getTitle() {
        return new Title("Plus court chemin", 6);
    }

    @Override
    protected List<JButton> getButtons() {
        return List.of(aButton()
                .withText("Retour")
                .withSize(200, 50)
                .isYCentered()
                .withAction(e -> graphUI.interact())
                .build());
    }

    @Override
    protected JPanel buildLeftPanel() {

        ScrollPaneBuilder builder1 = anHorizontalList()
                .withData(this.nodes)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(node1)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList1 = builder1
                .build();

        ScrollPaneBuilder builder2 = anHorizontalList()
                .withData(this.nodes)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(node2)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList2 = builder2
                .build();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> pathUI.interact(builder1.getSelectedValue(), builder2.getSelectedValue()))
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Départ : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList1)
                .addVerticalGlue()
                .add(aLabel().withText("Arrivée : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList2)
                .addVerticalGlue()
                .add(button)
                .addVerticalGlue()
                .build();
    }

    @Override
    protected JPanel buildRightPanel() {

        List<String> steps = path.getPath().stream()
                .map(step -> {
                    Link originLink = step.getOriginLink();
                    String link = "      " + (originLink != null ? "via " + originLink.getRoadNameWithIndex() + " (" + originLink.getDistance() + " km)" : "");

                    List<String> list = new ArrayList<>(List.of("=> " + step.getDestination().getName()));

                    if (!link.isBlank()) {
                        list.add(link);
                    }

                    list.add("");
                    return list;
                })
                .flatMap(List::stream)
                .toList();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addVerticalGlue()
                .add(aLabel().withText("Résultat : ").isXCentered().isTitle().build())
                .add(aLabel().withText("Distance : " + path.getDistance() + " km").isText().isXCentered().build())
                .add(aLabel().withText("Étapes : ").isText().isXCentered().build())
                .addRigidArea(0, 7)
                .add(anHorizontalList()
                        .withData(steps)
                        .withSize(250, 400)
                        .withSingleSelection()
                        .isYCentered()
                        .alwaysScrollVertical()
                        .alwaysScrollHorizontal()
                        .build())
                .addVerticalGlue()
                .build();
    }

    @Override
    public void leftClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : node1;

        pathUI.interact(newNode, node2);
    }

    @Override
    public void rightClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : node2;

        pathUI.interact(node1, newNode);
    }

}
