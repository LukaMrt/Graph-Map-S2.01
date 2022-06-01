package com.juka.graphmap.view.roads;

import com.google.inject.Inject;
import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.path.Path;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.home.HomeUI;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.ui.roads.RoadsView;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.SwingView;
import com.juka.graphmap.view.swing.components.ComboBoxBuilder;
import com.juka.graphmap.view.swing.components.ScrollPaneBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.ComboBoxBuilder.aComboBox;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

public class SwingRoadsView extends SwingView implements RoadsView {

    private final HomeUI homeUI;
    private final RoadsUI roadsUI;
    private List<String> steps;
    private Path path;
    private List<String> nodes;
    private String end;
    private String start;

    @Inject
    public SwingRoadsView(GraphMapJFrame frame, HomeUI homeUI, RoadsUI roadsUI) {
        super(frame);
        this.homeUI = homeUI;
        this.roadsUI = roadsUI;
    }

    @Override
    public void display(List<Node> nodes, List<String> steps, Path path, String start, String end) {
        this.nodes = nodes.stream().map(Node::getName).toList();
        this.steps = steps != null ? new ArrayList<>(steps) : new ArrayList<>();
        this.path = path != null ? path : new Path(new ArrayList<>(), 0d);
        this.start = start;
        this.end = end;
        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Cet écran permet de déterminer un chemin entre 2 lieux et passant par " +
                "des étapes intermédiaires données. Vous pouvez sélectionner les extrémités du " +
                "chemin et les étapes intermédiaires à gauche puis afficher le chemin (Ctrl ou Cmd " +
                "clic pour choisir plusieurs étapes dans la liste).\n\n" +
                "Pour l'interface graphique, les contrôles sont les suivants :\n" +
                "- Clic gauche pour sélectionner le départ lieu\n " +
                "- Clic droit pour sélectionner l'arrivée lieu\n" +
                "- Shift clic pour sélectionner et désélectionner une étape intermédiaire";
    }

    @Override
    protected Title getTitle() {
        return new Title("Routes", 7);
    }

    @Override
    protected List<JButton> getButtons() {
        return List.of(aButton().withText("Retour")
                .withSize(200, 50)
                .isYCentered()
                .withAction(e -> homeUI.interact())
                .build()
        );
    }

    @Override
    protected JPanel buildLeftPanel() {

        ComboBoxBuilder startBuilder = aComboBox()
                .withData(this.nodes)
                .withSize(200, 40)
                .withSelectedValue(start)
                .isYCentered();

        ScrollPaneBuilder stepsBuilder = anHorizontalList()
                .withData(this.nodes)
                .withSize(200, 200)
                .withMultipleSelection()
                .withSelectedValues(steps)
                .isYCentered()
                .alwaysScrollVertical()
                .neverScrollHorizontal();

        ComboBoxBuilder endBuilder = aComboBox()
                .withData(this.nodes)
                .withSize(200, 40)
                .withSelectedValue(end)
                .isYCentered();

        JButton button = aButton()
                .withText("Analyser")
                .withSize(100, 40)
                .isXCentered()
                .withAction(e -> roadsUI.interact(startBuilder.getSelectedValue(), stepsBuilder.getSelectedValues(), endBuilder.getSelectedValue()))
                .build();

        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .addRigidArea(0, 10)
                .add(aLabel().withText("Départ : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(startBuilder.build())
                .addVerticalGlue()
                .add(aLabel().withText("Étapes : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(stepsBuilder.build())
                .addVerticalGlue()
                .add(aLabel().withText("Arrivée : ").isXCentered().isTitle().build())
                .addRigidArea(0, 5)
                .add(endBuilder.build())
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
                .addRigidArea(0, 10)
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

        String newNode = node != null ? node.getName() : start;

        roadsUI.interact(newNode, steps, end);
    }

    @Override
    public void rightClick(Node node, Link link) {

        String newNode = node != null ? node.getName() : end;

        roadsUI.interact(start, steps, newNode);
    }

    @Override
    public void shiftClick(Node node, Link link) {

        if (node != null) {
            if (steps.contains(node.getName())) {
                steps.remove(node.getName());
            } else {
                steps.add(node.getName());
            }
        }

        roadsUI.interact(start, steps, end);
    }

}
