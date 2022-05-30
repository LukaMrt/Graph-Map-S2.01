package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.GlobalSwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;

public class NewSwingHomeView extends GlobalSwingView implements HomeView {

    private final GraphUI graphUI;
    private final RoadsUI roadsUI;
    private GraphCharacteristics graphCharacteristics;

    @Inject
    public NewSwingHomeView(GraphMapJFrame frame, GraphUI graphUI, RoadsUI roadsUI) {
        super(frame);
        this.graphUI = graphUI;
        this.roadsUI = roadsUI;
    }

    @Override
    public void display(GraphCharacteristics graphCharacteristics, List<Node> nodes) {
        this.graphCharacteristics = graphCharacteristics;
        super.display(nodes);
    }

    @Override
    protected JPanel buildTitle() {
        return aPanel(super.buildTitle())
                .add(aLabel().withText(graphCharacteristics.error)
                        .with20PlainFont()
                        .isXCentered()
                        .build())
                .build();
    }

    @Override
    protected Title getTitle() {
        return new Title("Accueil", 1);
    }

    @Override
    protected List<JButton> getButtons() {
        return List.of(
                aButton().withText("Graphe")
                        .withSize(200, 50)
                        .isYCentered()
                        .withAction(e -> graphUI.interact())
                        .build(),
                aButton().withText("Routes")
                        .withSize(200, 50)
                        .isYCentered()
                        .withAction(e -> roadsUI.interact(null, null, null))
                        .build()
        );
    }

    @Override
    protected JPanel buildLeftPanel() {
        return buildPanel(graphCharacteristics.locationCount + " lieux dont :",
                " - " + graphCharacteristics.cityPercentage + " % de villes",
                " - " + graphCharacteristics.restaurantPercentage + " % de restaurants",
                " - " + graphCharacteristics.recreationPercentage + " % de centres de loisirs");
    }

    @Override
    protected JPanel buildRightPanel() {
        return buildPanel(graphCharacteristics.roadCount + " routes dont :",
                " - " + graphCharacteristics.highwayPercentage + " % d'autoroutes",
                " - " + graphCharacteristics.nationalPercentage + " % de routes nationales",
                " - " + graphCharacteristics.departementalPercentage + " % de routes départementales");
    }

    private JPanel buildPanel(String text1, String text2, String text3, String text4) {
        return aPanel()
                .withYBoxLayout()
                .isXCentered()
                .add(aLabel().withText(text1).isTitle().build())
                .add(aLabel().withText(text2).isText().build())
                .add(aLabel().withText(text3).isText().build())
                .add(aLabel().withText(text4).isText().build())
                .build();
    }

}
