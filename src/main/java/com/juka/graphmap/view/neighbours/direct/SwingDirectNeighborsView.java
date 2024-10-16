package com.juka.graphmap.view.neighbours.direct;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeCharacteristics;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsUI;
import com.juka.graphmap.ui.neighbours.direct.DirectNeighborsView;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.SwingView;
import com.juka.graphmap.view.swing.components.ScrollPaneBuilder;

import javax.inject.Inject;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

/**
 * The direct neighbors view with graphical interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingDirectNeighborsView extends SwingView implements DirectNeighborsView {

    private static boolean firstTime = true;

    private final DirectNeighborsUI directNeighborsUI;
    private final GraphUI graphUI;

    private List<String> nodes;
    private List<String> links;
    private NodeCharacteristics nodeCharacteristics;
    private LinkCharacteristics linkCharacteristics;

    /**
     * Constructor.
     *
     * @param frame             the fram of the application
     * @param directNeighborsUI the direct neighbors ui
     * @param graphUI           the graph ui
     */
    @Inject
    public SwingDirectNeighborsView(GraphMapJFrame frame, DirectNeighborsUI directNeighborsUI, GraphUI graphUI) {
        super(frame);
        this.directNeighborsUI = directNeighborsUI;
        this.graphUI = graphUI;
    }

    @Override
    public void display(List<Node> nodes, List<String> links, NodeCharacteristics nodeCharacteristics, LinkCharacteristics linkCharacteristics) {
        this.nodes = nodes.stream().map(Node::getName).collect(Collectors.toList());
        this.links = links;
        this.nodeCharacteristics = nodeCharacteristics == null ? new NodeCharacteristics("", "", new ArrayList<>()) : nodeCharacteristics;
        this.linkCharacteristics = linkCharacteristics == null ? new LinkCharacteristics("", "", "", "", 0) : linkCharacteristics;
        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Cet écran permet de connaître les caractéristiques d'un noeud ou d'un lien " +
                "en donnant le nom, le type et les voisins directs pour un noeud et le nom, le type, " +
                "les extrémités et la distance pour un lien. Vous pouvez " +
                "sélectionner les noeuds à gauche pour les étudier et les liens à droite.\n\n" +
                "Pour l'interface graphique, les contrôles sont les suivants :\n" +
                "- Clic gauche pour sélectionner un noeud ou un lien et afficher ses caractéristiques.";
    }

    @Override
    protected Title getTitle() {
        return new Title("Voisinage direct", 3);
    }

    @Override
    protected List<JButton> getButtons() {
        return new ArrayList<>(Collections.singleton(aButton()
                .withText("Retour")
                .withSize(200, 50)
                .isYCentered()
                .withAction(e -> graphUI.interact())
                .build()));
    }

    @Override
    protected JPanel buildLeftPanel() {

        ScrollPaneBuilder builder = anHorizontalList()
                .withData(nodes)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(nodeCharacteristics.name)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList = builder
                .build();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> directNeighborsUI.interact(builder.getSelectedValue(), linkCharacteristics.name))
                .build();

        JScrollPane neighborsList = anHorizontalList()
                .withData(nodeCharacteristics.neighbors)
                .withSize(200, 100)
                .withSingleSelection()
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal()
                .build();

        JPanel descriptionPanel = aPanel()
                .withYBoxLayout()
                .add(aLabel().withText("Description du noeud :").isTitle().isXCentered().build())
                .add(aLabel().withText("Nom : " + nodeCharacteristics.name).isText().isXCentered().build())
                .add(aLabel().withText("Type : " + nodeCharacteristics.type).isText().isXCentered().build())
                .add(aLabel().withText("Voisins (" + nodeCharacteristics.neighbors.size() + ") :").isText().isXCentered().build())
                .addRigidArea(0, 10)
                .add(neighborsList)
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Liste des noeuds : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList)
                .addRigidArea(0, 15)
                .add(button)
                .addRigidArea(0, 15)
                .add(descriptionPanel)
                .addVerticalGlue()
                .build();
    }

    @Override
    protected JPanel buildRightPanel() {

        ScrollPaneBuilder builder = anHorizontalList()
                .withData(links)
                .withSize(200, 200)
                .withSingleSelection()
                .withSelectedValue(linkCharacteristics.name)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList = builder
                .build();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> directNeighborsUI.interact(nodeCharacteristics.name, builder.getSelectedValue()))
                .build();

        JPanel descriptionPanel = aPanel()
                .withYBoxLayout()
                .add(aLabel().withText("Description du lien :").isTitle().isXCentered().build())
                .add(aLabel().withText("Nom : " + linkCharacteristics.name).isText().isXCentered().build())
                .add(aLabel().withText("Type : " + linkCharacteristics.type).isText().isXCentered().build())
                .add(aLabel().withText("Allant de : " + linkCharacteristics.start).isText().isXCentered().build())
                .add(aLabel().withText("Vers : " + linkCharacteristics.end).isText().isXCentered().build())
                .add(aLabel().withText("Distance : " + linkCharacteristics.distance + " km").isText().isXCentered().build())
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Liste des liens : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList)
                .addRigidArea(0, 15)
                .add(button)
                .addRigidArea(0, 15)
                .add(descriptionPanel)
                .addVerticalGlue()
                .build();
    }

    @Override
    public void leftClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : nodeCharacteristics.name;
        String newLink = link != null ? link.getRoadNameWithIndex() : linkCharacteristics.name;

        directNeighborsUI.interact(newNode, newLink);
    }

    @Override
    protected boolean isFirstTime() {
        if (firstTime) {
            firstTime = false;
            return true;
        }
        return false;
    }

}
