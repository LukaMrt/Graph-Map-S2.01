package com.juka.graphmap.view.graph;

import com.juka.graphmap.domain.model.link.Link;
import com.juka.graphmap.domain.model.link.LinkType;
import com.juka.graphmap.domain.model.node.Node;
import com.juka.graphmap.domain.model.node.NodeType;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.juka.graphmap.view.swing.components.ButtonBuilder.aButton;
import static com.juka.graphmap.view.swing.components.LabelBuilder.aLabel;
import static com.juka.graphmap.view.swing.components.PanelBuilder.aPanel;
import static com.juka.graphmap.view.swing.components.ScrollPaneBuilder.anHorizontalList;

/**
 * The graph view with graphical interface.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class SwingGraphView extends SwingView implements GraphView {

    private static boolean firstTime = true;

    private final CompareUI compareUI;
    private final PathUI pathUI;
    private final DirectNeighborsUI directNeighborsUI;
    private final IndirectNeighborsUI indirectNeighborsUI;
    private final HomeUI homeUI;

    private List<String> nodes;
    private List<String> links;

    /**
     * Constructor.
     *
     * @param frame               the frame of the application
     * @param compareUI           the compare UI
     * @param pathUI              the path UI
     * @param directNeighborsUI   the direct neighbors UI
     * @param indirectNeighborsUI the indirect neighbors UI
     * @param homeUI              the home UI
     */
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
    public void display(List<Node> nodes, List<Link> links) {

        this.nodes = new ArrayList<>();

        this.nodes.add(" => Villes :");
        nodes.stream().filter(node -> node.getType().equals(NodeType.CITY)).forEach(node -> this.nodes.add(node.getName()));

        this.nodes.add("");
        this.nodes.add(" => Restaurants :");
        nodes.stream().filter(node -> node.getType().equals(NodeType.RESTAURANT)).forEach(node -> this.nodes.add(node.getName()));

        this.nodes.add("");
        this.nodes.add(" => Centre de loisir :");
        nodes.stream().filter(node -> node.getType().equals(NodeType.RECREATION_CENTER)).forEach(node -> this.nodes.add(node.getName()));


        this.links = new ArrayList<>();

        this.links.add(" => Autoroutes :");
        links.stream().filter(link -> link.getType().equals(LinkType.HIGHWAY)).forEach(link -> this.links.add(link.getRoadNameWithIndex()));

        this.links.add("");
        this.links.add(" => Routes nationales :");
        links.stream().filter(link -> link.getType().equals(LinkType.NATIONAL)).forEach(link -> this.links.add(link.getRoadNameWithIndex()));

        this.links.add("");
        this.links.add(" => Routes départementales :");
        links.stream().filter(link -> link.getType().equals(LinkType.DEPARTMENTAL)).forEach(link -> this.links.add(link.getRoadNameWithIndex()));

        super.display(nodes);
    }

    @Override
    protected String getHelp() {
        return "Cet écran décrit le graphe en donnant la liste des noeuds et la liste des liens. " +
                "Aucune action n'est disponible ici.";
    }

    @Override
    protected Title getTitle() {
        return new Title("Graphe", 2);
    }

    @Override
    protected List<JButton> getButtons() {
        return Arrays.asList(
                aButton().withText("Description")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> directNeighborsUI.interact(null, null))
                        .build(),
                aButton().withText("n-distance")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> indirectNeighborsUI.interact(null, 2))
                        .build(),
                aButton().withText("Retour")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> homeUI.interact())
                        .build(),
                aButton().withText("Comparaison")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> compareUI.interact(null, null))
                        .build(),
                aButton().withText("Chemin")
                        .withSize(180, 50)
                        .isYCentered()
                        .withAction(e -> pathUI.interact(null, null))
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

    @Override
    protected boolean isFirstTime() {
        if (firstTime) {
            firstTime = false;
            return true;
        }
        return false;
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
