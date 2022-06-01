package com.juka.graphmap.view.neighbours.indirect;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.indirect.IndirectNeighborsView;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.SwingView;
import com.juka.graphmap.view.swing.components.ScrollPaneBuilder;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

public class SwingIndirectNeighborsView extends SwingView implements IndirectNeighborsView {

    private final GraphUI graphUI;
    private final IndirectNeighborsUI indirectNeighborsUI;

    private List<String> nodes;
    private String location1;
    private String location2;
    private boolean result;

    @Inject
    public SwingIndirectNeighborsView(GraphMapJFrame frame, GraphUI graphUI, IndirectNeighborsUI indirectNeighborsUI) {
        super(frame);
        this.graphUI = graphUI;
        this.indirectNeighborsUI = indirectNeighborsUI;
    }

    @Override
    public void display(List<Node> nodes, String location1, String location2, boolean result) {
        this.nodes = nodes.stream().map(Node::getName).toList();
        this.location1 = location1 != null ? location1 : "";
        this.location2 = location2 != null ? location2 : "";
        this.result = result;
        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Clic droit pour sélectionner la première ville, clic gauche pour sélectionner la seconde ville et savoir si elles sont à 2-distance.";
    }

    @Override
    protected Title getTitle() {
        return new Title("Voisinage indirect", 4);
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
                .withSelectedValue(location1)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList1 = builder1
                .build();

        ScrollPaneBuilder builder2 = anHorizontalList()
                .withData(this.nodes)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(location2)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList2 = builder2
                .build();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> indirectNeighborsUI.interact(builder1.getSelectedValue(), builder2.getSelectedValue()))
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Premier noeud : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList1)
                .addVerticalGlue()
                .add(aLabel().withText("Second noeud : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList2)
                .addVerticalGlue()
                .add(button)
                .addVerticalGlue()
                .build();
    }

    @Override
    protected JPanel buildRightPanel() {

        String message = "ne sont pas à 2-distances.";

        if (result) {
            message = "sont à 2-distances.";
        }

        return aPanel()
                .withYBoxLayout()
                .addVerticalGlue()
                .add(aLabel().withText("Résultat :").isTitle().isXCentered().build())
                .add(aLabel().withText(this.location1).isText().isXCentered().build())
                .add(aLabel().withText("&").isText().isXCentered().build())
                .add(aLabel().withText(this.location2).isText().isXCentered().build())
                .add(aLabel().withText(message).isText().isXCentered().build())
                .addVerticalGlue()
                .build();
    }

    @Override
    public void leftClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : location1;

        indirectNeighborsUI.interact(newNode, location2);
    }

    @Override
    public void rightClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : location2;

        indirectNeighborsUI.interact(location1, newNode);
    }

}
