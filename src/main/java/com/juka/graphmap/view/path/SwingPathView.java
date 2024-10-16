package com.juka.graphmap.view.path;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.path.PathUI;
import com.juka.graphmap.ui.path.PathView;
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
 * The path view with graphical interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingPathView extends SwingView implements PathView {

    private static boolean firstTime = true;

    private final GraphUI graphUI;
    private final PathUI pathUI;
    private List<String> nodes;
    private String node1;
    private String node2;
    private Path path;

    /**
     * Constructor.
     *
     * @param frame   the frame of the application
     * @param graphUI the graph UI
     * @param pathUI  the path UI
     */
    @Inject
    public SwingPathView(GraphMapJFrame frame, GraphUI graphUI, PathUI pathUI) {
        super(frame);
        this.graphUI = graphUI;
        this.pathUI = pathUI;
    }

    @Override
    public void display(List<Node> nodes, String node1, String node2, Path path) {
        this.nodes = nodes.stream().map(Node::getName).collect(Collectors.toList());
        this.node1 = node1;
        this.node2 = node2;
        this.path = path;
        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Cet écran permet de déterminer le chemin le plus court entre 2 lieux. Vous pouvez " +
                "sélectionner le départ et l'arrivé à gauche puis afficher le chemin avec le bouton " +
                "Analyser.\n\n" +
                "Pour l'interface graphique, les contrôles sont les suivants :\n" +
                "- Clic gauche pour sélectionner le premier lieu\n " +
                "- Clic droit pour sélectionner le second lieu";
    }

    @Override
    protected Title getTitle() {
        return new Title("Plus court chemin", 6);
    }

    @Override
    protected List<JButton> getButtons() {
        return Collections.singletonList(aButton()
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

                    List<String> list = new ArrayList<>(Collections.singletonList("=> " + step.getDestination().getName()));

                    if (!link.trim().isEmpty()) {
                        list.add(link);
                    }

                    list.add("");
                    return list;
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

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

    @Override
    protected boolean isFirstTime() {
        if (firstTime) {
            firstTime = false;
            return true;
        }
        return false;
    }

}
