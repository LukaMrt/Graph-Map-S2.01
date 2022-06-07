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
import static com.juka.graphmap.view.swing.components.ComboBoxBuilder.aComboBox;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

/**
 * The indirect neighbors view with graphical interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingIndirectNeighborsView extends SwingView implements IndirectNeighborsView {

    private static boolean firstTime = true;

    private final GraphUI graphUI;
    private final IndirectNeighborsUI indirectNeighborsUI;

    private List<String> nodes;
    private String node;
    private int distance;
    private List<Node> result;

    /**
     * Constructor.
     *
     * @param frame               the frame of the application
     * @param graphUI             the graph UI
     * @param indirectNeighborsUI the indirect neighbors UI
     */
    @Inject
    public SwingIndirectNeighborsView(GraphMapJFrame frame, GraphUI graphUI, IndirectNeighborsUI indirectNeighborsUI) {
        super(frame);
        this.graphUI = graphUI;
        this.indirectNeighborsUI = indirectNeighborsUI;
    }

    @Override
    public void display(List<Node> nodes, String node, int distance, List<Node> result) {
        this.nodes = nodes.stream().map(Node::getName).toList();
        this.node = node != null ? node : "";
        this.result = result;
        this.distance = distance;
        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Cet écran permet de déterminer si 2 lieux sont à n-distance ou non. 2 lieux sont " +
                "à n-distance si le chemin le plus court entre les 2 contient n étapes. Vous pouvez " +
                "sélectionner les 1 lieux et la distance à gauche puis afficher tous les lieux à n-distance " +
                "du lieu sélectionné avec le bouton Analyser.\n\n" +
                "Pour l'interface graphique, les contrôles sont les suivants :\n" +
                "- Clic gauche pour sélectionner la ville";
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
                .withSize(200, 150)
                .withSingleSelection()
                .withSelectedValue(node)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        JScrollPane nodeList1 = builder1
                .build();

        JComboBox<String> comboBox = aComboBox()
                .withData(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
                .isXCentered()
                .withSize(200, 50)
                .withSelectedValue(String.valueOf(distance))
                .build();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> indirectNeighborsUI.interact(builder1.getSelectedValue(), Integer.parseInt((String) comboBox.getSelectedItem())))
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Noeud à étudier : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(nodeList1)
                .addVerticalGlue()
                .add(comboBox)
                .addVerticalGlue()
                .add(button)
                .addVerticalGlue()
                .build();
    }

    @Override
    protected JPanel buildRightPanel() {

        JScrollPane scrollPane = anHorizontalList()
                .withData(this.result.stream().map(Node::getName).toList())
                .withSize(200, 200)
                .withSingleSelection()
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal()
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Résultat :").isTitle().isXCentered().build())
                .add(aLabel().withText("Les " + result.size() + " noeuds à " + distance + "-distance de ").isText().isXCentered().build())
                .add(aLabel().withText(node + " sont :").isText().isXCentered().build())
                .addRigidArea(0, 10)
                .add(scrollPane)
                .build();
    }

    @Override
    public void leftClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : this.node;

        indirectNeighborsUI.interact(newNode, distance);
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
