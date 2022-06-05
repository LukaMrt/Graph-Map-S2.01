package com.juka.graphmap.view.home;

import com.juka.graphmap.domain.model.graph.GraphCharacteristics;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.view.Title;
import com.juka.graphmap.ui.graph.GraphUI;
import com.juka.graphmap.ui.home.HomeView;
import com.juka.graphmap.ui.roads.RoadsUI;
import com.juka.graphmap.view.frame.GraphMapJFrame;
import com.juka.graphmap.view.swing.SwingView;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;

/**
 * The home view with graphical interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingHomeView extends SwingView implements HomeView {

    private static boolean firstTime = true;

    private final GraphUI graphUI;
    private final RoadsUI roadsUI;
    private GraphCharacteristics graphCharacteristics;

    /**
     * Constructor.
     *
     * @param frame   the frame of the application
     * @param graphUI the graph view
     * @param roadsUI the roads view
     */
    @Inject
    public SwingHomeView(GraphMapJFrame frame, GraphUI graphUI, RoadsUI roadsUI) {
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
    protected String getHelp() {
        return "Cet écran décrit le graphe en donnant le nombre d'éléments et le pourcentage de" +
                "chaque type de noeud et de lien par rapport à l'ensemble des éléments. " +
                "Aucune action n'est disponible ici.";
    }

    @Override
    protected Title getTitle() {
        return new Title("Accueil (" + graphCharacteristics.error + ")", 1);
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
                .add(aLabel().withText(text2).withColor(Color.GREEN).isText().build())
                .add(aLabel().withText(text3).withColor(Color.RED).isText().build())
                .add(aLabel().withText(text4).withColor(Color.BLUE).isText().build())
                .build();
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
